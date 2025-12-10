package org.astronex.olyn.domain.model

import androidx.compose.ui.graphics.Color
import kotlinx.datetime.format
import org.astronex.olyn.domain.enums.CyclePhase
import org.astronex.olyn.util.LocalDateUtil
import org.astronex.olyn.util.LocalDateUtil.toEpochDay

/**
 * Cycle represents a simplified menstrual cycle model for tracking and prediction purposes.
 *
 * This data class models a complete menstrual cycle with its four distinct phases:
 *  - Menstruation _ Giai đoạn hành kinh: [cycleStartEpochDay] to calculated period end
 *  - Follicular _ Giai đoạn trứng phát triển: After period ends to fertile window start
 *  - Fertile _ Thời kì rụng trứng (Thời kỳ dễ mang thai) | 7 ngày nếu chu kì đều & không đều thì dùng công thức khác: [fertileStartEpochDate] to [fertileLength] with calculated ovulation day
 *  - Luteal _ Giai đoạn hoàng thể (Thời kì phục hồi): After fertile window to cycle end
 *
 * Key Features:
 * - Automatically calculates cycle end date and next cycle start based on cycle length
 * - Determines ovulation day using standard 14-day rule before next cycle
 * - Provides fertile window boundaries for pregnancy planning
 * - Uses typical 28-day cycle length as default
 *
 * Usage:
 * This class is designed for scenarios where you need a straightforward cycle representation
 * with predefined ovulation windows, suitable for basic cycle tracking and fertility awareness.
 *
 * @param [cycleStartEpochDay] The first day of the menstrual period (epoch day) _ ngày bắt đầu của chu kỳ kinh nguyệt
 * @param [cycleLength] The total length of the cycle in days, defaults to 28 _ độ dài của chu kỳ kinh nguyệt
 * @param [menstruationEndEpochDay] The last day of the menstrual period (epoch day) _ ngày kết thúc của giai đoạn hành kinh
 * @param [fertileStartEpochDate] The first day of the ovulation window (epoch day) _ ngày bắt đầu Thời kỳ dễ thụ thai (dễ mang thai)
 * @param [fertileLength] The last day of the ovulation window (epoch day) _ ngày kết thúc Thời kỳ dễ thụ thai (dễ mang thai)
 *
 * [fertileStartEpochDate] & [fertileLength] được tính dựa vào việc chu kì kinh nguyệt đều hoặc không đều.
 * Nếu chu kì đều thì dùng công thức: Ngày rụng trứng = Ngày đầu kỳ kinh tiếp theo − 14
 * Nếu chu kì không đều thì dùng công thức F_first = max(S_min - 18, L_min + G) & F_last = min(S_max - 11, N - L_min)
 *
 *
 * Class này có thể cung cấp
 * + ngày bắt đầu của toàn bộ chu kỳ kinh nguyệt [cycleStartEpochDay]
 * + ngày kết thúc của toàn bộ chu kỳ kinh nguyệt [cycleEndEpochDay]
 * + ngày bắt đầu của chu kỳ tiếp theo [nextCycleStartDate]
 *
 * + ngày kết thúc của giai đoạn hành kinh [menstruationEndEpochDay]
 * + độ dài của giai đoạn hành kinh [menstruationLength]
 *
 * + ngày bắt đầu của giai đoạn dễ mang thai [fertileStartEpochDate]
 * + độ dài của giai đoạn dễ mang thai [fertileLength]
 * + trong giai đoạn dễ mang thai, có thể tính toán ngày rụng trứng [ovulationEpochDate] với chu kì đều (Regular Cycle).
 * Chu kì không đều (Irregular Cycle) thì không tính toán được
 *
 * # Question:
 * 1. Chu ki trung binh (F-03) co khac voi chu ki kinh nguyet (F-01) khong?
 * 2. Ngay dau tien cua ki kinh tiep theo(F-02) & Ngay dau ki kinh tiep theo trong cong thuc Ngày rụng trứng = Ngày đầu kỳ kinh tiếp theo − 14(F-06)
 * cung 1 y nghia?
 * 3. ngay bat dau/ ket thuc thu thai(F-06) co khac voi ngay bat dau/ ket thuc rụng trứng(F-05) khong?
 * 4. periodEndEpochDay tuc ngày kết thúc của giai đoạn hành kinh nghia la gi? No co khac voi ngay ket thuc cua chu ki kinh nguyet(endEpochDay) khong?
 * */
data class Cycle(
    val cycleStartEpochDay: Long = LocalDateUtil.now().toEpochDay(),
    val cycleLength: Int = TYPICAL_CYCLE_LENGTH,
    val menstruationEndEpochDay: Long = LocalDateUtil.now().toEpochDay(),
    val fertileStartEpochDate: Long = LocalDateUtil.now().toEpochDay(),
    val fertileLength: Int = 5,
    val ovulationEpochDate: Long  = LocalDateUtil.now().toEpochDay(),
) {
    /******************
     * - Relate to cycle
     *
     * @property nextCycleStartDate is the first day of next cycle _ ngày bắt đầu của chu kỳ tiếp theo tức cũng
     * là ngày bắt đầu của giai đoạn hành kinh tiếp theo
     *
     * @property cycleEndEpochDay is the last day of cycle _ ngày kết thúc của chu kỳ kinh nguyệt
     * */
    val cycleEndEpochDay: Long = cycleStartEpochDay + cycleLength - 1
    val nextCycleStartDate: Long = cycleStartEpochDay + cycleLength


    /******************
     * - Relate to menstruation
     *
     * menstruationLength is the length of menstruation _ độ dài của giai đoạn hành kinh
     */
    val menstruationLength = (menstruationEndEpochDay - cycleStartEpochDay + 1).toInt()


    /******************
     * Determines if a cycle is acceptable for use in calculations and predictions.
     *
     * A cycle is acceptable if the non-menstrual days exceed 18 days, ensuring
     * sufficient time for follicular and luteal phases.
     *
     * @return true if cycle is acceptable for calculations, false otherwise
     */

    // old version | đây là điều kiện mới để một chu kì được coi là bình thường,tức là chu kỳ kinh nguyệt có thể được sử dụng trong các tính toán và dự đoán
    // Không show fetile window nếu Cycle length < (14 days (Tính Ovulation Day) + 5 days (5 days trươc rụng trứng) + Số ngày hành kinh)
    // ->fun isAcceptable(): Boolean = cycleLength - menstruationLength > BEFORE_FIRST_DAY_FERTILE_WINDOW

    // new version | đây là điều kiện mới để một chu kì được coi là bình thường,tức là chu kỳ kinh nguyệt có thể được sử dụng trong các tính toán và dự đoán
    // Không show fetile window nếu Fertile Window Length > Cycle Length
    fun isAcceptable(): Boolean = cycleLength > fertileLength



    /******************
     * Rong kinh is when the menstruation length is longer than the cycle length _ Rong kinh là khi thời gian hành kinh dài hơn thời gian chu kỳ kinh nguyệt
     */
    fun isRongKinh(): Boolean = menstruationLength >= cycleLength

    /**
     * fertile end epoch date is the end day of fertile phase _ ngày kết thúc giai đoạn dễ mang thai
     */
    val fertileEndEpochDate: Long = fertileStartEpochDate + fertileLength - 1

    /**
     *  - Relate to follicular
     *
     *  @property follicularStartEpochDay is the start day of follicular _ ngày bắt đầu giai đoạn trứng phát triển
     *  @property follicularEndEpochDay is the end day of follicular _ ngày kết thúc giai đoạn rụng trứng phát triển
     */
    val follicularStartEpochDay: Long = menstruationEndEpochDay + 1
    val follicularEndEpochDay: Long = fertileStartEpochDate - 1

    override fun toString(): String{
        val formatter = LocalDateUtil.formatterDDMMYYY
        val cycleStartDay = LocalDateUtil.ofEpochDay(cycleStartEpochDay)
        val cycleEndDay = LocalDateUtil.ofEpochDay(cycleEndEpochDay)
        val menstruationEndDay = LocalDateUtil.ofEpochDay(menstruationEndEpochDay)

        val follicularStartEpochDay = LocalDateUtil.ofEpochDay(follicularStartEpochDay)
        val follicularEndEpochDay = LocalDateUtil.ofEpochDay(follicularEndEpochDay)

        val fertileStartEpochDate = LocalDateUtil.ofEpochDay(fertileStartEpochDate)
        val fertileEndEpochDate = LocalDateUtil.ofEpochDay(fertileEndEpochDate)

        val ovulationDay = LocalDateUtil.ofEpochDay(ovulationEpochDate)
        val nextCycleStartDay = LocalDateUtil.ofEpochDay(nextCycleStartDate)

        val regularCycle = if(isAcceptable()) "bình thường" else "bất thường"

        return "Cycle----->"+
                "\nChu kì kinh nguyệt $regularCycle bắt đầu từ ngày ${cycleStartDay.format(formatter)} đến ngày ${cycleEndDay.format(formatter)} kéo dài $cycleLength ngày"+
                "\nGiai đoạn rụng trứng (menstruation) kéo dài $menstruationLength ngày bắt đầu từ ngày ${cycleStartDay.format(formatter)} đến ngày ${menstruationEndDay.format(formatter)}"+
                "\nGiai đoạn trứng phát triển (follicular) bắt đầu từ ngày ${follicularStartEpochDay.format(formatter)} đến ngày ${follicularEndEpochDay.format(formatter)}"+
                "\nGiai đoạn dễ mang thai (fertile) bắt đầu từ ngày ${fertileStartEpochDate.format(formatter)} đến ngày ${fertileEndEpochDate.format(formatter)} với ${ovulationDay.format(formatter)} là ngày rụng trứng" +
                "\nNgày bắt đầu chu kì kinh nguyệt tiếp theo là ngày ${nextCycleStartDay.format(formatter)}"
    }

    fun getPhase(day: Long): CyclePhase {
        return when (day) {
            in cycleStartEpochDay..menstruationEndEpochDay -> CyclePhase.Menstruation
            in menstruationEndEpochDay + 1 until fertileStartEpochDate -> CyclePhase.Follicular
            in fertileStartEpochDate until (fertileStartEpochDate + fertileLength) -> CyclePhase.Fertile
            else -> CyclePhase.Luteal
        }
    }

    fun getDaysOfPhase(epochDay: Long): Pair<CyclePhase, Int> {
        return when (getPhase(epochDay)) {
            CyclePhase.Menstruation -> {
                CyclePhase.Menstruation to (epochDay - cycleStartEpochDay + 1).toInt()
            }

            CyclePhase.Follicular -> {
                CyclePhase.Follicular to (epochDay - menstruationEndEpochDay).toInt()
            }

            CyclePhase.Fertile -> {
                CyclePhase.Fertile to (epochDay - fertileStartEpochDate + 1).toInt()
            }

            else -> {
                CyclePhase.Luteal to (epochDay - (fertileStartEpochDate + fertileLength) + 1).toInt()
            }
        }
    }

    companion object {
        const val TYPICAL_CYCLE_LENGTH = 28 // Chu kỳ kinh nguyệt của 1 người điển hình là 28 ngày
        const val OVULATION_BEFORE_NEW_CYCLE = 14 // Số ngày trước khi chu kỳ mới bắt đầu mà rụng trứng xảy ra
        const val FERTILE_WINDOW_BEFORE_OVULATION = 5 // Số ngày trước ngày rụng trứng trong giai đoạn dễ thụ thai
        const val FERTILE_WINDOW_AFTER_OVULATION = 1 // Số ngày sau ngày rụng trứng trong giai đoạn dễ thụ thai
        const val BEFORE_FIRST_DAY_FERTILE_WINDOW = 18 // Số ngày trước ngày đầu tiên của giai đoạn dễ thụ thai
        const val BEFORE_LAST_DAY_FERTILE_WINDOW = 11 // Số ngày trước ngày cuối cùng của giai đoạn dễ thụ thai
        const val TYPICAL_SHORTEST_LUTEAL_DURATION = 10 // Số ngày ngắn nhất của giai đoạn Lutual phase
        const val TYPICAL_BUFFER_PERIOD = 2 // Tổng số ngày hành kinh ngắn nhất
        const val IQR_MULTIPLIER = 1.5 // Hằng số trong công thức IQR
        const val THE_9_DAY_THRESHOLD = 9 // Ngưỡng giới hạn 9 ngày trong công thức xác định chu kì
        const val THE_SHORTEST_NUMBER_OF_DAYS_IN_THE_LUTEAL_PHASE = 10 // Số ngày ngắn nhất của giai đoạn Lutual phase
        const val THE_SHORTEST_TOTAL_NUMBER_OF_MENSTRUATION_DAYS = 2 // Tổng số ngày hành kinh ngắn nhất
        const val PREDICT_MENSTRUATION_LENGTH = 5 // Độ dài của giai đoạn kinh nguyệt dự đoán, thường là 5 ngày
        const val NORMAL_OVULATION_BEFORE = 5
        const val NORMAL_OVULATION_AFTER = 1

        /**
         * menstruationColor la mau cua giai doan kinh nguyet
         * predictPeriodColor is mau cua giai doan du doan kinh nguyet
         * fertileColor is mau cua giai doan Thời kì rụng trứng (Thời kỳ dễ thụ thai)
         */
        val menstruationColor = Color(0xFFFF86B1)
        val predictMenstruationColor = Color(0xFFFFB7D0)
        val fertileColor = Color(0xFFC89DFF)



        fun generateFakeData(): List<Cycle> {
            return listOf(
                Cycle(
                    cycleStartEpochDay = 5985,
                    cycleLength = 28,
                    menstruationEndEpochDay = 123456789,
                    fertileStartEpochDate = 123456789,
                    fertileLength = 5
                ),
                Cycle(
                    cycleStartEpochDay = 123456789,
                    cycleLength = 28,
                    menstruationEndEpochDay = 123456789,
                    fertileStartEpochDate = 123456789,
                    fertileLength = 5
                ),
                Cycle(
                    cycleStartEpochDay = 123456789,
                    cycleLength = 28,
                    menstruationEndEpochDay = 123456789,
                    fertileStartEpochDate = 123456789,
                    fertileLength = 5
                ),
                Cycle(
                    cycleStartEpochDay = 123456789,
                    cycleLength = 28,
                    menstruationEndEpochDay = 123456789,
                    fertileStartEpochDate = 123456789,
                    fertileLength = 5
                )
            )
        }
        fun belongToRegularCycle(epochDay: Long, listOfCycle: List<Cycle>): Boolean {
            val currentCycle = listOfCycle.find { cycle -> (cycle.cycleStartEpochDay  <= epochDay && epochDay <= cycle.cycleEndEpochDay) }
            return currentCycle?.isAcceptable() ?: false
        }

    }
}