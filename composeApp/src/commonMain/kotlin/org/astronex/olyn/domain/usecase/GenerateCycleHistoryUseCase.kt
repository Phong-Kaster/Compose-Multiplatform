package org.astronex.olyn.domain.usecase


import com.apero.cycletracker.domain.cyclebyphong.usecase.GenerateCycleUseCase
import org.astronex.olyn.domain.model.Cycle
import org.astronex.olyn.domain.model.MenstruationDay


/**
 * Use case for generating cycle history based on a list of menstruation days and the average cycle length.
 * Cycle history includes past cycles and the current cycle.
 *
 * This use case processes the list of menstruation days to create a history of cycles
 * including their start and end dates, lengths, fertile windows, and ovulation days.
 * It groups consecutive menstruation days into menstrual periods and calculates
 * the cycle details based on these periods.
 * @param listOfMenstruationDay List of menstruation days to analyze for cycle history.
 * @param userAverageCycleLength Average cycle length used for calculations, especially for the current cycle.
 * @return List of Cycle representing the history of cycles.
 */
class GenerateCycleHistoryUseCase(
    private val listOfMenstruationDay: List<MenstruationDay>,
    private val userAverageCycleLength: Int
) {
    private val TAG = this::class.simpleName
    operator fun invoke(): List<Cycle> {
        if (listOfMenstruationDay.isEmpty()) return listOf()

        var listOfCycle = mutableListOf<Cycle>()
        // Group consecutive period days together into separate menstrual periods
        val listOfMenstrualPeriod =
            groupConsecutivePeriodDays(listOfMenstruationDay = listOfMenstruationDay)

//        LogUtil.logcat(
//            tag = TAG,
//            message = "groupConsecutivePeriodDays = ${listOfMenstrualPeriod.map { menstruationDays -> menstruationDays.map { it.localDate.toString() +"(${it.epochDay})" }}}"
//        )

        /*
        * For example
        * menstruationDaysInThisPeriod has this data [01-07, 02-07], [05-07, 06-07], [10-07, 11-07, 12-07], [14-07, 15-07]
        *
        * [14-07, 15-07] is the last cycle & is also the current cycle
        * */

        listOfMenstrualPeriod.forEachIndexed { index, menstruationDaysInThisPeriod ->
            if (index != listOfMenstrualPeriod.size - 1) {

                // For cycles in the past (not the current cycle)
                val cycleStartEpochDay = menstruationDaysInThisPeriod.first().epochDay
                val nextCycleStartEpochDay = listOfMenstrualPeriod[index + 1].first().epochDay

                val cycleLength = (nextCycleStartEpochDay - cycleStartEpochDay).toInt()
                val ovulationDay = nextCycleStartEpochDay - Cycle.OVULATION_BEFORE_NEW_CYCLE

                val cycle = Cycle(
                    cycleStartEpochDay = cycleStartEpochDay,
                    cycleLength = cycleLength,
                    menstruationEndEpochDay = menstruationDaysInThisPeriod.last().epochDay,
                    fertileStartEpochDate = ovulationDay - Cycle.NORMAL_OVULATION_BEFORE, // old logic dang bi lenh 1 ngay bat dau so voi anh Long, ben anh Long bat dau tu ngay 1 thi ben nay dang bat dau tu ngay 2 doi voi fertile window
                    fertileLength = Cycle.NORMAL_OVULATION_BEFORE + Cycle.NORMAL_OVULATION_AFTER + 1,
                    ovulationEpochDate = ovulationDay,
                )

                //LogUtil.logcat(tag = TAG, message = "cycle $index has length ${cycle.cycleLength} with acceptable ${cycle.isAcceptable()}")


                listOfCycle.add(cycle)
            } else {
                // last cycle is current cycle
                // For the current cycle (the last menstrual period in the list)
                // Use GenerateCycleUseCase instead of local buildCurrentCycle
                val currentCycle: Cycle? = GenerateCycleUseCase(
                    listOfCycle = listOfCycle,
                    listOfMenstruationDay = menstruationDaysInThisPeriod,
                    userAverageCycleLength = userAverageCycleLength
                ).invoke()

                if (currentCycle == null) return emptyList()


                listOfCycle.add(currentCycle)
            }
        }

        return listOfCycle
    }

    /**
     * Groups consecutive period days together into separate menstrual periods.
     * For example: [Day1, Day2, Day3, Day7, Day8] becomes [[Day1, Day2, Day3], [Day7, Day8]]
     * Each group [Day1, Day2, Day3] or [Day7, Day8] is menstrual period
     */
    private fun groupConsecutivePeriodDays(listOfMenstruationDay: List<MenstruationDay>): List<List<MenstruationDay>> {
        if (listOfMenstruationDay.isEmpty()) return emptyList()

        val listOfMenstrualPeriod = mutableListOf<MutableList<MenstruationDay>>()
        var currentPeriodGroup = mutableListOf(listOfMenstruationDay.first())

        // Check each day to see if it's consecutive to the previous day
        for (dayIndex in 1 until listOfMenstruationDay.size) {
            val currentDay = listOfMenstruationDay[dayIndex]
            val previousDay = listOfMenstruationDay[dayIndex - 1]

            // If the current day is the next day after the previous day
            // for instance: current day is Day2 & previous Day is Day 1, add it to the current group
            if (currentDay.epochDay == previousDay.epochDay + 1) {
                currentPeriodGroup.add(currentDay)
            } else {
                // If there's a gap, start a new menstrual period group
                listOfMenstrualPeriod.add(currentPeriodGroup)
                currentPeriodGroup = mutableListOf(currentDay)
            }
        }

        // Don't forget to add the last group
        listOfMenstrualPeriod.add(currentPeriodGroup)

        // Return only non-empty groups
        return listOfMenstrualPeriod.filter { it.isNotEmpty() }
    }
}