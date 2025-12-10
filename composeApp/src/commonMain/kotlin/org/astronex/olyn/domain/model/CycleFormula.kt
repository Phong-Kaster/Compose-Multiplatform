package org.astronex.olyn.domain.model

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * Cycle represents a simplified menstrual cycle model for tracking and prediction purposes.
 *
 * This data class models a complete menstrual cycle with its four distinct phases:
 *  - Menstruation _ Giai đoạn hành kinh: [cycleStartEpochDay] to calculated period end
 *  - Follicular _ Giai đoạn trứng phát triển: After period ends to fertile window start
 *  - Fertile _ Thời kì rụng trứng (Thời kỳ dễ thụ thai) | 7 ngày nếu chu kì đều & không đều thì dùng công thức khác: [fertileStartEpochDate] to [fertileEndEpochDate] with calculated ovulation day
 *  - Luteal _ Giai đoạn hoàng thể (Thời kì phục hồi): After fertile window to cycle end
 *
 * Key Features:
 * - Automatically calculates cycle end date and next cycle start based on cycle length
 * - Determines ovulation day using standard 14-day rule before next cycle
 * - Provides fertile window boundaries for pregnancy planning
 * - Uses typical 28-day cycle length as default
 */
object CycleFormula {

    /**
     * Xác định chu kỳ regular or irregular -> Xác định chu kỳ kinh nguyệt có đều đặn hay không
     *
     * Determines whether menstrual cycles are regular based on the variability
     * of cycle length differences between consecutive cycles.
     *
     * This function analyzes cycle regularity by calculating the median of absolute
     * differences between consecutive cycle lengths and comparing it against a
     * 9-day threshold. A cycle is considered regular if the median difference
     * is less than 9 days.
     *
     * Algorithm:
     * 1. Calculate absolute differences between consecutive cycle lengths
     * 2. Sort the differences and find the median
     * 3. Compare median against 9-day threshold for regularity determination
     *
     * Regularity Criteria:
     * - Regular: Median difference < 9 days
     * - Irregular: Median difference ≥ 9 days
     * - Edge case: Less than 2 cycles defaults to regular
     *
     * Use Cases:
     * - Determining appropriate prediction algorithms (regular vs irregular)
     * - Personalizing fertility tracking recommendations
     * - Identifying patterns in menstrual cycle data
     *
     * @param listOfCycle List of menstrual cycles to analyze for regularity
     * @return true if cycles are regular (median difference < 9 days), false otherwise
     *
     * Example:
     * ```
     * val cycles = listOf(
     *     Cycle(startDay = 100, cycleLength = 28, ...),
     *     Cycle(startDay = 128, cycleLength = 30, ...),
     *     Cycle(startDay = 158, cycleLength = 27, ...)
     * )
     * val isRegular = isRegularCycle(cycles) // Returns true if median difference < 9
     * ```
     */
    fun isRegularCycle(listOfCycle: List<Cycle>): Boolean {
        // Need at least 2 cycles to calculate differences
        if (listOfCycle.size < 2) return true

        // Step 1: Calculate differences between consecutive cycle lengths
        val cycleDifferences = mutableListOf<Int>()
        for (i in 1 until listOfCycle.size) {
            val difference = abs(listOfCycle[i].cycleLength - listOfCycle[i - 1].cycleLength)
            cycleDifferences.add(difference)
        }

        // Step 2: Sort differences and find median
        val sortedDifferences = cycleDifferences.sorted()
//        val median = if (sortedDifferences.size % 2 == 1) {
//            // Odd number: middle value
//            sortedDifferences[sortedDifferences.size / 2].toDouble()
//        } else  {
//            // Even number: average of two middle values
//            val mid1 = sortedDifferences[sortedDifferences.size / 2 - 1]
//            val mid2 = sortedDifferences[sortedDifferences.size / 2]
//            (mid1 + mid2) / 2.0
//        }
        val median = computeMedian(sortedDifferences)

        // Step 3: Compare to 9-day threshold
        return median < Cycle.Companion.THE_9_DAY_THRESHOLD
    }

    private fun computeMedian(sortedList: List<Int>): Double {
        return if (sortedList.size % 2 == 1) {
            sortedList[sortedList.size /2].toDouble()
        } else  {
            val mid1 = sortedList[sortedList.size / 2 - 1]
            val mid2 = sortedList[sortedList.size / 2]
            (mid1 + mid2) / 2.0
        }
    }

    /**
     * - Calculate Median
     *
     * Calculates the median (middle value) of a list of integers.
     *
     * For lists with even number of elements, returns the average of the two middle values.
     * For lists with odd number of elements, returns the exact middle value.
     * Returns default cycle length if the list is empty.
     *
     * @param data List of integers to find median for
     * @return The median value as a Double
     */
     fun median(data: List<Int>): Double {
        if (data.isEmpty()) return Cycle.TYPICAL_CYCLE_LENGTH.toDouble() // todo
        return if (data.size % 2 == 0) {
            (data[data.size / 2 - 1] + data[data.size / 2]) / 2.0
        } else {
            data[data.size / 2].toDouble()
        }
    }

    /**
     * Xác định giá trị bất thường (Outliers) bằng IQR
     *
     * Filters cycles to remove statistical outliers using the Inter Quartile Range (IQR) method.
     *
     * This function implements outlier detection to identify and exclude cycles with abnormal lengths
     * that may skew cycle predictions and statistics. It uses the standard statistical approach
     * of identifying outliers as values that fall outside 1.5 * IQR from the first and third quartiles.
     *
     * Algorithm:
     * 1. Sort cycle lengths in ascending order
     * 2. Calculate Q1 (25th percentile) and Q3 (75th percentile)
     * 3. Compute IQR = Q3 - Q1
     * 4. Define outlier boundaries: [Q1 - 1.5*IQR, Q3 + 1.5*IQR]
     * 5. Filter cycles whose lengths fall within these boundaries
     *
     * Use Cases:
     * - Data preprocessing before cycle analysis
     * - Improving prediction accuracy by removing anomalous cycles
     * - Quality control for menstrual cycle tracking data
     *
     * @param listOfCycle List of cycles to filter for outliers
     * @return List of cycles with outliers removed, maintaining original order
     *
     * Example:
     * ```
     * val cycles = listOf(
     *     Cycle(startDay = 100, cycleLength = 28, ...),
     *     Cycle(startDay = 128, cycleLength = 45, ...), // potential outlier
     *     Cycle(startDay = 173, cycleLength = 30, ...)
     * )
     * val filteredCycles = getCyclesStayOnBoundary(cycles)
     * ```
     */
    fun removeOutlinerWithIQR(listOfCycle: List<Cycle>): List<Cycle> {
        if (listOfCycle.isEmpty()) return emptyList()

        val listOfAcceptableCycle =
            listOfCycle.filter { it.isAcceptable() }.sortedBy { it.cycleStartEpochDay }

        // Step 1: Sort cycle lengths
        val cycleLengths = listOfAcceptableCycle.map { it.cycleLength }.sorted()

        // Step 2: Calculate Q1, Q3, and IQR
//        val q1 = calculateBaseOnPercentage(cycleLengths, 25.0)
//        val q3 = calculateBaseOnPercentage(cycleLengths, 75.0)
        val quartile = calculateQuartile(sortedData = cycleLengths)
        val q1 = quartile.first
        val q3 = quartile.second
        val iqr = q3 - q1

        // Step 3: Compute outlier boundaries
        val lowerBound = q1 - Cycle.Companion.IQR_MULTIPLIER * iqr
        val upperBound = q3 + Cycle.Companion.IQR_MULTIPLIER * iqr

        // Step 4: Filter cycles within acceptable range
        return listOfAcceptableCycle.filter { cycle ->
            lowerBound <= cycle.cycleLength && cycle.cycleLength <= upperBound
        }
    }

    //Tính tứ phân vị, trả về Pair<Q1, Q3>
    private fun calculateQuartile(sortedData: List<Int>): Pair<Double, Double> {
        val midIndex = (sortedData.size - 1)/2
        var leftList = emptyList<Int>()
        var rightList = emptyList<Int>()

        if (sortedData.size % 2 == 0) {
            leftList = sortedData.take(midIndex + 1)
            rightList = sortedData.takeLast(midIndex + 1)
        } else {
            leftList = sortedData.take(midIndex)
            rightList = sortedData.takeLast(midIndex)
        }
        val q1 = median(leftList)
        val q3 = median(rightList)

        return Pair(q1,q3)
    }

    private fun calculateBaseOnPercentage(sortedData: List<Int>, percentage: Double): Double {
        if (sortedData.isEmpty()) return 0.0

        val index = (percentage / 100.0) * (sortedData.size - 1)
        val lowerIndex = index.toInt()
        val upperIndex = min(lowerIndex + 1, sortedData.size - 1)
        val weight = index - lowerIndex

        return sortedData[lowerIndex] + weight * (sortedData[upperIndex] - sortedData[lowerIndex])
    }

    fun computeAverageLengthLeanOnWMA(
        averageCycleLength: Int = Cycle.TYPICAL_CYCLE_LENGTH,
        listOfCycle: List<Cycle>
    ): Int {
        val listOfAcceptableCycle = removeOutlinerWithIQR(listOfCycle)
        return when (listOfAcceptableCycle.size) {
            0 -> averageCycleLength

            1 -> listOfAcceptableCycle.first().cycleLength

            else -> {
                val total = listOfAcceptableCycle.sortedBy { it.cycleStartEpochDay }.foldIndexed(
                    0
                ) { index: Int, total: Int, it: Cycle ->
                    total + (it.cycleLength * (index + 1))
                }
                val cycleSize = listOfAcceptableCycle.size
                (total.toFloat() / (cycleSize * (cycleSize + 1) / 2)).roundToInt()
            }
        }
    }

     fun calculateFertileStartAndEndDate(
         listOfCycle: List<Cycle>,
         averageLength: Int,
         cycleStartEpochDay: Long,
         cycleEndEpochDay: Long,
         menstruationEndEpochDay: Long
    ): FertilePhase {
        return if (isRegularCycle(listOfCycle)) {
            calculateRegularCycleFertileWindow(cycleEndEpochDay, menstruationEndEpochDay)
        } else {
            calculateIrregularCycleFertileWindow(
                listOfCycle,
                averageLength,
                cycleStartEpochDay,
                menstruationEndEpochDay
            )
        }
    }

    fun calculateRegularCycleFertileWindow(
        endEpochDay: Long,
        periodEndEpochDay: Long
    ): FertilePhase {
        val ovulationDay = endEpochDay - Cycle.OVULATION_BEFORE_NEW_CYCLE + 1
        val fertileStartDay = ovulationDay - Cycle.NORMAL_OVULATION_BEFORE
        val fertileEndDay = ovulationDay + Cycle.NORMAL_OVULATION_AFTER

        return if (fertileEndDay <= periodEndEpochDay) {
            // Fertile window ends before period ends - invalid
            FertilePhase(startDay = -1, length = 0, ovulationDay = -1)
        } else {
            val length = (fertileEndDay - fertileStartDay + 1).toInt() // độ dài của chu kì dễ mang thai
            FertilePhase(
                startDay = fertileStartDay,
                length = length,
                ovulationDay = ovulationDay
            )
        }
    }

    fun calculateIrregularCycleFertileWindow(
        validCycles: List<Cycle>,
        avgCycleLengthWMA: Int,
        startEpochDay: Long,
        menstruationEndEpochDay: Long
    ): FertilePhase {
        val fertileWindowRange = predictFirstAndLastFertileWindow(validCycles, avgCycleLengthWMA)
        val fertileStartDay = startEpochDay + fertileWindowRange.first - 1
        val fertileEndDay = startEpochDay + fertileWindowRange.second - 1

        return if (fertileEndDay <= menstruationEndEpochDay) {
            // Fertile window ends before period ends - invalid
            FertilePhase(startDay = -1, length = 0, ovulationDay = -1)
        } else {
            val length = (fertileEndDay - fertileStartDay + 1).toInt()
            FertilePhase(startDay = fertileStartDay, length = length, ovulationDay = -1)
        }
    }

    /**
     * - Predict First And Last Fertile Window
     *
     * Related to irregular cycles prediction
     *
     * Document: https://confluence.apehero.com/display/AI/Menstruation+Tracking
     *
     * Calculates the fertile window range for irregular cycles by analyzing historical cycle data.
     * This method is used when cycles are irregular (median variation >= 9 days) and standard
     * ovulation prediction methods are not reliable.
     *
     * The fertile window is calculated using conservative boundaries based on:
     * - Shortest cycle: Determines earliest possible fertile start
     * - Longest cycle: Determines latest possible fertile end
     * - Average cycle: Ensures luteal phase constraints are respected
     *
     * Algorithm:
     * 1. Find shortest and longest cycle lengths from historical data
     * 2. Calculate earliest fertile start: max of (shortest - 18 days, luteal_min + period_min)
     * 3. Calculate latest fertile end: min of (longest - 11 days, average - luteal_min)
     * 4. Return the range as day numbers within the cycle
     *
     * Safety constraints:
     * - Ensures minimum luteal phase of 10 days
     * - Accounts for minimum period length of 2 days
     * - Uses conservative 18/11 day rules for irregular cycles
     *
     * @param listOfCycle Historical cycles to analyze for fertile window calculation
     * @param averageLength Average cycle length for luteal phase validation
     * @return Pair of (fertileStartDay, fertileEndDay) as day numbers within cycle
     */
     fun predictFirstAndLastFertileWindow(
        listOfCycle: List<Cycle>,
        averageLength: Int
    ): Pair<Int, Int> {

        // Find the shortest and longest cycle lengths from historical data
        // Uses normal cycle length as fallback if no cycles available
        val shortestCycle =
            if (listOfCycle.isNotEmpty()) listOfCycle.minOf { it.cycleLength } else Cycle.TYPICAL_CYCLE_LENGTH
        val longestCycle =
            if (listOfCycle.isNotEmpty()) listOfCycle.maxOf { it.cycleLength } else Cycle.TYPICAL_CYCLE_LENGTH

        // Calculate earliest possible fertile window start day
        // Uses max of: (shortest - 18 days) or (luteal_min + period_min)
        val fertileWindowStartDay = max(
            shortestCycle - Cycle.BEFORE_FIRST_DAY_FERTILE_WINDOW,
            Cycle.THE_SHORTEST_NUMBER_OF_DAYS_IN_THE_LUTEAL_PHASE + Cycle.THE_SHORTEST_TOTAL_NUMBER_OF_MENSTRUATION_DAYS
        )

        // Calculate latest possible fertile window end day
        // Uses min of: (longest - 11 days) or (average - luteal_min)
        val fertileWindowEndDay = min(
            longestCycle - Cycle.BEFORE_LAST_DAY_FERTILE_WINDOW,
            averageLength - Cycle.THE_SHORTEST_NUMBER_OF_DAYS_IN_THE_LUTEAL_PHASE
        )

        return fertileWindowStartDay to fertileWindowEndDay
    }
}