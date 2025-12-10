package org.astronex.olyn.view.model

import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.runtime.Stable
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.day
import compose_multiplatform.composeapp.generated.resources.days
import compose_multiplatform.composeapp.generated.resources.high_chances
import compose_multiplatform.composeapp.generated.resources.highest_chances
import compose_multiplatform.composeapp.generated.resources.low_chances
import compose_multiplatform.composeapp.generated.resources.of_period
import compose_multiplatform.composeapp.generated.resources.ovulation_day
import compose_multiplatform.composeapp.generated.resources.pregnancy
import compose_multiplatform.composeapp.generated.resources.until_fertile_window
import compose_multiplatform.composeapp.generated.resources.until_ovulation
import compose_multiplatform.composeapp.generated.resources.until_ovulation_day
import compose_multiplatform.composeapp.generated.resources.until_period
import org.astronex.olyn.domain.enums.CyclePhase
import org.astronex.olyn.domain.model.Cycle
import org.astronex.olyn.util.LocalDateUtil
import org.astronex.olyn.util.LocalDateUtil.toEpochDay
import org.jetbrains.compose.resources.getString

/**
 * Represents a single day in the calendar with menstrual cycle information.
 *
 * @param epochDay The day represented as epoch day (days since 1970-01-01)
 * @param isMenstruationDay True if this day is part of the menstruation period
 * @param isOvulationDay True if this day is the ovulation day
 * @param isPredictedMenstruation True if this day is predicted to be a menstruation day
 * @param isPredictedOvulation True if this day is predicted to be an ovulation day
 * @param cyclePhase The current cycle phase for this day
 * @param cycleDayNumber The day number within the current cycle
 * @param hasSymptoms True if the user has recorded symptoms for this day
 * @param isSelected True if this day is currently selected in the UI
 *
 * @param isCurrentCycle True if this day is part of the current cycle
 * @param isPredictedCycle True if this day is part of a predicted cycle
 *
 * @property isToday True if this day is today
 * @property isReallyMenstruationDay True if this day is a menstruation day (either confirmed or predicted)
 * @property isReallyOvulationDay True if this day is an ovulation day (either confirmed or predicted)
 *
 * @sample
 * ```
 * // Create a menstruation day
 * val menstruationDay = MenstruationDayProperty(
 *     epochDay = LocalDateUtil.of(2024, 1, 15).toEpochDay(),
 *     isMenstruationDay = true,
 *     cyclePhase = CyclePhase.Menstruation,
 *     cycleDayNumber = 3
 * )
 *
 * // Create an ovulation day
 * val ovulationDay = MenstruationDayProperty(
 *     epochDay = LocalDateUtil.of(2024, 1, 28).toEpochDay(),
 *     isOvulationDay = true,
 *     cyclePhase = CyclePhase.Ovulation,
 *     cycleDayNumber = 14
 * )
 * ```
 */
@Stable
data class MenstruationDayProperty(
    val epochDay: Long = LocalDateUtil.now().toEpochDay(),

    val isMenstruationDay: Boolean = false,
    val isOvulationDay: Boolean = false,

    val isPredictedMenstruation: Boolean = false,
    val isPredictedOvulation: Boolean = false,

    val cyclePhase: CyclePhase = CyclePhase.None,
    val cycleDayNumber: Int? = null,

    val isFertileDay: Boolean = false,
    val isPredictedFertileDay: Boolean = false,

    val hasSymptoms: Boolean = false,
    val isSelected: Boolean = false,
    val isCurrentCycle: Boolean = false,
    val isPredictedCycle: Boolean = false,
    val isCompletedCycle: Boolean = false,
) {
    val isToday: Boolean = epochDay == LocalDateUtil.now().toEpochDay()
    val isReallyMenstruationDay = isMenstruationDay || isPredictedMenstruation
    val isReallyOvulationDay = isOvulationDay || isPredictedOvulation

//    val cyclePhase: CyclePhase = when {
//        isReallyMenstruationDay -> CyclePhase.Menstruation
//        isReallyOvulationDay || isFertileDay || isPredictedFertileDay -> CyclePhase.Fertile
//        else -> CyclePhase.Follicular
//    }

    companion object {
        suspend fun generateOverallContent(
            menstruationDayProperty: MenstruationDayProperty,
            currentCycle: Cycle
        ): Triple<String, String, String> {
            when (menstruationDayProperty.cyclePhase) {
                CyclePhase.Menstruation -> {
                    val dayInMenstruationPhase =
                        currentCycle.menstruationLength - (currentCycle.menstruationEndEpochDay - menstruationDayProperty.epochDay).toInt()
                    val title =
                        getString(Res.string.day) + " $dayInMenstruationPhase" // Day 1
                    val subtitle = getString(Res.string.of_period) // Of Period
                    val pregnancyChance =
                        "${getString(Res.string.pregnancy)}: ${getString(Res.string.low_chances)}" // Pregnancy: Low chances
                    return Triple(first = title, second = subtitle, third = pregnancyChance)
                }

                CyclePhase.Follicular -> {
                    //There are 2 case when chosen menstruation days belongs to Follicular
                    // Case 1: Cycle is normal and can calculate ovulation day
                    val dayUntilOvulation = currentCycle.ovulationEpochDate - menstruationDayProperty.epochDay
                    val cycleCanCalculateOvulationDay = dayUntilOvulation > 0
//                    LogUtil.logcat(tag = "TAG", message = "Follicular - cycleCanCalculateOvulationDay = $cycleCanCalculateOvulationDay", enableDivider = true)
                    if (cycleCanCalculateOvulationDay) {
                        val title = "$dayUntilOvulation ${getString(Res.string.days)}" // 2 Days
                        val subtitle = getString(Res.string.until_ovulation) // Until Ovulation
                        val pregnancyChance = "${getString(Res.string.pregnancy)}: ${getString(Res.string.low_chances)}" // Pregnancy: Low chances
                        return Triple(first = title, second = subtitle, third = pregnancyChance)
                    } else {
                        // Case 2: cycle is abnormal & can not calculate ovulation day, now we calculate how many days until fertile phase
                        val daysUntilFertile = currentCycle.fertileStartEpochDate - menstruationDayProperty.epochDay
                       val title =  if (daysUntilFertile > 1)
                            "$daysUntilFertile ${getString(Res.string.days)}" // 14 Days Until Ovulation Day
                        else
                            "$daysUntilFertile ${getString(Res.string.day)}" // 1 Day Until Ovulation Day
                        val subtitle  = getString(Res.string.until_fertile_window) // Until Fertile Window
                        val pregnancyChance = "${getString(Res.string.pregnancy)}: ${getString(Res.string.low_chances)}" // Pregnancy: Low chances
                        return Triple(first = title, second = subtitle, third = pregnancyChance)
                    }

                }

                CyclePhase.Fertile -> {
                    // There are 3 cases need to be attention:
                    // Case 1: day is ovulation day &
                    // Case 2: normal days belong to fertile phase & before ovulation day
                    // Case 3: normal days belong to fertile phase & after ovulation day

                    val localDate = LocalDateUtil.ofEpochDay(menstruationDayProperty.epochDay)
                    val ovulationLocalDate = LocalDateUtil.ofEpochDay(currentCycle.ovulationEpochDate)
                    val isBeforeOvulationDay: Boolean = localDate < ovulationLocalDate

                    val title = when {
                        menstruationDayProperty.isOvulationDay -> getString(Res.string.ovulation_day)
                        isBeforeOvulationDay -> {
                            val daysUntilOvulationDay = currentCycle.ovulationEpochDate - menstruationDayProperty.epochDay
                            if (daysUntilOvulationDay > 1)
                                "$daysUntilOvulationDay ${getString(Res.string.days)}" // 14 Days Until Ovulation Day
                            else
                                "$daysUntilOvulationDay ${getString(Res.string.day)}" // 1 Day Until Ovulation Day
                        }
                        else -> {
                            val daysUntilNextCycle = currentCycle.nextCycleStartDate - menstruationDayProperty.epochDay
                            if (daysUntilNextCycle > 1)
                                "$daysUntilNextCycle ${getString(Res.string.days)}" // 14 Days Until Next Cycle
                            else
                                "$daysUntilNextCycle ${getString(Res.string.day)}" // 1 Day Until Next Cycle
                        }
                    }
                    val subtitle = when {
                        menstruationDayProperty.isOvulationDay -> ""
                        isBeforeOvulationDay -> getString(Res.string.until_ovulation_day)
                        else -> getString(Res.string.until_period)
                    }


                    val pregnancyChance =  if(menstruationDayProperty.isOvulationDay)
                        "${getString(Res.string.pregnancy)}: ${getString(Res.string.highest_chances)}" // Pregnancy: Highest chances
                    else
                        "${getString(Res.string.pregnancy)}: ${getString(Res.string.high_chances)}"
                    return Triple(first = title, second = subtitle, third = pregnancyChance)
                }
                CyclePhase.Luteal -> {
                    val daysUntilNextCycle = currentCycle.nextCycleStartDate - menstruationDayProperty.epochDay
                    val title = when {
                        menstruationDayProperty.isOvulationDay -> getString(Res.string.ovulation_day)
                        else -> {
                            if (daysUntilNextCycle > 1)
                                "$daysUntilNextCycle ${getString(Res.string.days)}" // 14 Days Until Next Cycle
                            else
                                "$daysUntilNextCycle ${getString(Res.string.day)}" // 1 Day Until Next Cycle
                        }
                    }
                    val subtitle = when {
                        menstruationDayProperty.isOvulationDay -> ""
                        else -> getString(Res.string.until_period)
                    }
                    val pregnancyChance = "${getString(Res.string.pregnancy)}: ${getString(Res.string.low_chances)}" // Pregnancy: Low chances
                    return Triple(first = title, second = subtitle, third = pregnancyChance)
                }
                else -> return Triple("", "", "")
            }
        }
    }
}