package com.apero.cycletracker.domain.cyclebyphong.usecase

import org.astronex.olyn.domain.model.Cycle
import org.astronex.olyn.domain.model.CycleFormula.calculateFertileStartAndEndDate
import org.astronex.olyn.domain.model.CycleFormula.computeAverageLengthLeanOnWMA
import org.astronex.olyn.domain.model.CycleFormula.removeOutlinerWithIQR
import org.astronex.olyn.domain.model.MenstruationDay


/**
 * This function build current cycle based on historical data.
 * Use case for generating current menstrual cycle predictions based on historical data.
 *
 * Analyzes past cycles and current menstruation to calculate cycle phases including
 * fertile window and ovulation timing for both regular and irregular cycles.
 *
 * @param listOfCycle Historical cycles for pattern analysis and regularity determination
 * @param listOfMenstruationDay Current menstruation days to define cycle boundaries
 * @param userAverageCycleLength Default cycle length used as fallback in calculations
 * @return Cycle an cycle object containing calculated cycle details
 * @author Phong-Kaster
 */
class GenerateCycleUseCase(
    private val listOfCycle: List<Cycle>,
    private val listOfMenstruationDay: List<MenstruationDay>,
    private val userAverageCycleLength: Int
) {
    operator fun invoke(): Cycle? {
        if (listOfMenstruationDay.isEmpty()) return null

        /*listOfAcceptableCycle là tập hợp các cycle hợp lệ sau phép lọc IQR*/
        val listOfAcceptableCycle = removeOutlinerWithIQR(listOfCycle = listOfCycle)

        /*cycleLength là độ dài chu kì kinh nguyệt dùng công thức trung bình động có trọng số WMA(Weighted Moving Average)*/
        val cycleLength = computeAverageLengthLeanOnWMA(
            averageCycleLength = userAverageCycleLength,
            listOfCycle = listOfAcceptableCycle
        )

        //LogUtil.logcat(tag = "GenerateCycleUseCase", message = "AverageLengthLeanOnWMA = $cycleLength")


        /*ngày bắt đầu & kết thúc toàn bộ chu kì kinh nguyệt*/
        val cycleStartEpochDay = listOfMenstruationDay.first().epochDay
        val cycleEndEpochDay = cycleStartEpochDay + cycleLength - 1


        // ngày kết thúc giai đoạn hành kinh
        val menstruationEndEpochDay = listOfMenstruationDay.last().epochDay

        val fertileData = calculateFertileStartAndEndDate(
            listOfCycle = listOfAcceptableCycle,
            averageLength = cycleLength,
            cycleStartEpochDay = cycleStartEpochDay,
            cycleEndEpochDay = cycleEndEpochDay,
            menstruationEndEpochDay = menstruationEndEpochDay
        )

        val outcome = Cycle(
            cycleStartEpochDay = cycleStartEpochDay,
            cycleLength = cycleLength,
            menstruationEndEpochDay = menstruationEndEpochDay,
            fertileLength = fertileData.length,
            fertileStartEpochDate = fertileData.startDay,
            ovulationEpochDate = fertileData.ovulationDay
        )
        return outcome
    }
}