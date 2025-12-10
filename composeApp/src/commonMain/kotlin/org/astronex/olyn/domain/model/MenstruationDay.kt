package org.astronex.olyn.domain.model


import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.number
import kotlinx.datetime.plus
import org.astronex.olyn.util.LocalDateUtil
import org.astronex.olyn.util.LocalDateUtil.toEpochDay
import org.astronex.olyn.util.platform.logcat

/**
 * Menstruation Day đại diện cho những ngày thuộc chu kì hành kinh trên lịch
 */
data class MenstruationDay(
    val epochDay: Long = LocalDateUtil.now().toEpochDays(),
) {
    val localDate: LocalDate
        get() = LocalDateUtil.ofEpochDay(epochDay)


    fun LocalDate.format(pattern: String = "dd/MM/yyyy"): String {
        return pattern
            .replace(oldValue = "dd", newValue = this.day.toString().padStart(2, '0'))
            .replace(oldValue = "MM", newValue = this.month.number.toString().padStart(2, '0'))
            .replace(oldValue = "yyyy", newValue = this.year.toString())
    }


    companion object {
        fun generateListOfFakeData(): List<MenstruationDay> {
            val localDate = LocalDateUtil.now()
            val outcome = mutableListOf<MenstruationDay>()
            //val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")


            for (i in 0L..4L) {
//                val date = localDate.plusDays(i)
                val date = localDate.plus(value = i, unit = DateTimeUnit.DAY)
                val epochDay = date.toEpochDay()
                val menstruationDay = MenstruationDay(epochDay)


//                if (i == 0L) {
                logcat(
                    tag = "TAG",
                    message = "Start from first day: ${date.format(LocalDateUtil.formatterDDMMYYY)} (epochDay: $epochDay)"
                )
//                }
//                if (i == 4L) {
//                    logcat(tag ="TAG", message = "End at last day: ${date.format(formatter)} (epochDay: $epochDay)")
//                }

                outcome.add(menstruationDay)
            }

            return outcome
        }

        /**
         * @author Phong-Kaster
         * find exactly what actually cycle's start day
         * for example: there are a group of days include: 29-06, 01-07, 02-07, 03-07 then cycle's start day must be cycle's start day
         */
        fun computeCycleStartEpochDay(listOfMenstruationDay: List<MenstruationDay>): MenstruationDay {
            if (listOfMenstruationDay.isEmpty()) {
                logcat(
                    tag = "CycleStartDay",
                    message = "listOfMenstruationDay is empty, return today as cycle start day",
                    enableHorizontalLine = true,
                )
                return MenstruationDay() // Return today as cycle start day
            }

            // By default, take menstruation day that has biggest epoch day as cycle start day
            var cycleStartDay = listOfMenstruationDay.maxBy { it.epochDay }
            logcat(
                tag = "CycleStartDay",
                message = "initial cycle start day at ${cycleStartDay.localDate}",
                enableHorizontalLine = true,
            )

            // Sort the list of menstruation day descending. For example: listOf( 30-06, 26-06, 21-06, 01-05)
            val sortedList = listOfMenstruationDay.sortedByDescending { it.epochDay }
            logcat(
                tag = "CycleStartDay",
                message = "sorted list: ${sortedList.joinToString { it.localDate.toString() }}"
            )

            // Loop through sorted list to find exactly what cycleStartDay
            for (index in 0 until sortedList.size) {
                val menstruationDay = sortedList[index]

                val epochDay = menstruationDay.epochDay
                val previousEpochDay = epochDay - 1


                val menstruationDayExistBeforeDay =
                    sortedList.any { it -> it.epochDay == previousEpochDay }
                logcat(
                    tag = "CycleStartDay",
                    message = "process with index: $index at ${menstruationDay.localDate}(${menstruationDay.epochDay}) & " +
                            "previousDay ${MenstruationDay(previousEpochDay).localDate} with $menstruationDayExistBeforeDay"
                )
                if (!menstruationDayExistBeforeDay) {
                    cycleStartDay = menstruationDay
                    break
                }
            }

            logcat(
                tag = "CycleStartDay",
                message = "final cycle start day at ${cycleStartDay.localDate}",
            )

            return cycleStartDay
        }

        fun areNext5DaysNotMenstruationDay(
            menstruationDay: MenstruationDay,
            listOfMenstruationDay: List<MenstruationDay>
        ): Boolean {
            for (i in 1L..5L) {
                val nextEpochDay = menstruationDay.epochDay + i
                // Check if there is already some days as menstruation day
                val isNextDayMenstruationDay =
                    listOfMenstruationDay.any { it -> it.epochDay == nextEpochDay }

                if (isNextDayMenstruationDay) {
                    return false
                }
            }

            return true
        }

        fun isYesterdayMenstruationDay(
            menstruationDay: MenstruationDay,
            listOfMenstruationDay: List<MenstruationDay>
        ): Boolean {
            val yesterdayEpochDay = menstruationDay.epochDay - 1
            return listOfMenstruationDay.any { it -> it.epochDay == yesterdayEpochDay }
        }
    }
}
