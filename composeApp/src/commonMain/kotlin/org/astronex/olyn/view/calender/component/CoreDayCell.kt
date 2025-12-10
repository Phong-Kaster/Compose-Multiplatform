package org.astronex.olyn.view.calender.component

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.ic_egg
import compose_multiplatform.composeapp.generated.resources.today
import org.astronex.olyn.domain.enums.CyclePhase
import org.astronex.olyn.domain.enums.Weekday
import org.astronex.olyn.domain.model.MenstruationDay
import org.astronex.olyn.ui.modifier.dynamicCycleThemeColor
import org.astronex.olyn.ui.modifier.singleClickable
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.astronex.olyn.util.LocalDateUtil
import org.astronex.olyn.util.LocalDateUtil.toEpochDay
import org.astronex.olyn.view.model.MenstruationDayProperty
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * A composable function that renders a circular day cell for the menstrual cycle calendar.
 * The cell displays the day of the month and applies different visual styling based on the
 * cycle phase and whether it's the currently selected day.
 *
 * @param chosenMenstruationDay The currently selected menstruation day to compare against
 * @param localDate The date this cell represents
 * @param menstruationDayPropertyProperty Properties containing cycle phase and other day-specific information
 * @param onClick Callback function triggered when the cell is clicked
 *
 * Visual styling logic:
 * - **Selected day (chosenMenstruationDay == localDate)**:
 *   - Menstruation/Fertile phases: Colored background with outer shadow and white border
 *   - Follicular/Luteal phases: Light gray background with subtle shadow and gray border
 *   - Other phases: Light gray background with minimal shadow
 *
 * - **Non-selected days**:
 *   - Menstruation/Fertile phases: Colored border and background for current cycle days, transparent for predicted cycles
 *   - Other phases: Transparent styling
 *
 * Text color is determined by background contrast:
 * - White text on colored backgrounds
 * - Black text on light/transparent backgrounds
 */
//
//@Composable
//fun CoreCell(
//    isRegularCycle: Boolean,
//    chosenMenstruationDay: MenstruationDay,
//    localDate: LocalDate,
//    menstruationDayPropertyProperty: MenstruationDayProperty,
//    onClick: () -> Unit = {},
//) {
//    //val isRegularCycle by remember(menstruationDayPropertyProperty){ mutableStateOf(menstruationDayPropertyProperty.isRegularCycle) }
//
//    Box(
//        contentAlignment = Alignment.Center, modifier = Modifier
//            .requiredSize(32.dp)
//            .then(
//                // there are 2 case: chosen menstruation day is today & otherwise
//                if (chosenMenstruationDay.epochDay == localDate.toEpochDay()) {
//                    // Case 1: chosenMenstruationDay is localDate
//                    // Case 1.1: chosenMenstruationDay's cycle phase is CyclePhase.Menstruation or CyclePhase.Fertile
//                    when (menstruationDayPropertyProperty.cyclePhase) {
//                        CyclePhase.Menstruation -> {
//                            if (
//                                (menstruationDayPropertyProperty.isCurrentCycle && localDate == LocalDateUtil.now()
//                                )
//                                ||
//                                (menstruationDayPropertyProperty.isCurrentCycle && localDate < LocalDateUtil.now())
//                                ||
//                                (menstruationDayPropertyProperty.isCompletedCycle)
//                            ) {
//                                // Case 1.1.1: chosen menstruation day's cycle phase is CyclePhase.Menstruation & belongs to current
//                                // or there days belongs to completed cycle
//                                Modifier
////                                    .outerShadow(
////                                        shadowRadius = 1.5.dp,
////                                        spreadRadius = 1.5.dp,
////                                        shadowColor = dynamicCycleThemeColor(cyclePhase = menstruationDayPropertyProperty.cyclePhase),
////                                        shape = CircleShape
////                                    )
//                                    .border(width = 1.dp, color = Color.White, shape = CircleShape)
//                                    .background(
//                                        color = dynamicCycleThemeColor(cyclePhase = menstruationDayPropertyProperty.cyclePhase),
//                                        shape = CircleShape,
//                                    )
//                            } else {
//                                // Case 1.1.2: chosen menstruation day's cycle phase is CyclePhase.Menstruation & NOT belongs to current cycle
//                                Modifier
////                                    .outerShadow(
////                                        shadowRadius = 1.5.dp,
////                                        spreadRadius = 1.5.dp,
////                                        shadowColor = dynamicCycleThemeColor(cyclePhase = menstruationDayPropertyProperty.cyclePhase),
////                                        shape = CircleShape
////                                    )
//                                    .border(width = 1.dp, color = Color.White, shape = CircleShape)
//                                    .background(
//                                        color = Color(0xFFF2F2F7),
//                                        shape = CircleShape
//                                    )
//                            }
//                        }
//
//                        CyclePhase.Fertile -> {
//                            if (menstruationDayPropertyProperty.isCurrentCycle && isRegularCycle
//                                ||
//                                (menstruationDayPropertyProperty.isCompletedCycle && isRegularCycle)
//                            ) {
//                                // Case 1.1.1: chosen menstruation day's cycle phase is CyclePhase.Menstruation & belongs to current cycle
//                                // or there days belongs to completed cycle & are before today
//                                Modifier
////                                    .outerShadow(
////                                        shadowRadius = 1.5.dp,
////                                        spreadRadius = 1.5.dp,
////                                        shadowColor = dynamicCycleThemeColor(cyclePhase = menstruationDayPropertyProperty.cyclePhase),
////                                        shape = CircleShape
////                                    )
//                                    .border(width = 1.dp, color = Color.White, shape = CircleShape)
//                                    .background(
//                                        color = dynamicCycleThemeColor(cyclePhase = menstruationDayPropertyProperty.cyclePhase),
//                                        shape = CircleShape,
//                                    )
//                            } else if (menstruationDayPropertyProperty.isPredictedCycle) {
//                                Modifier
////                                    .outerShadow(
////                                        shadowRadius = 1.5.dp,
////                                        spreadRadius = 1.5.dp,
////                                        shadowColor = dynamicCycleThemeColor(cyclePhase = menstruationDayPropertyProperty.cyclePhase),
////                                        shape = CircleShape
////                                    )
//                                    .border(
//                                        width = 1.dp,
//                                        color = dynamicCycleThemeColor(cyclePhase = menstruationDayPropertyProperty.cyclePhase),
//                                        shape = CircleShape
//                                    )
//                                    .background(
//                                        color = Color(0xFFF2F2F7),
//                                        shape = CircleShape
//                                    )
//                            } else {
//                                Modifier
////                                    .outerShadow(
////                                        shadowRadius = 1.5.dp,
////                                        spreadRadius = 1.5.dp,
////                                        shadowColor = dynamicCycleThemeColor(cyclePhase = menstruationDayPropertyProperty.cyclePhase),
////                                        shape = CircleShape
////                                    )
//                                    .border(width = 1.dp, color = Color.White, shape = CircleShape)
//                                    .background(
//                                        color = Color(0xFFF2F2F7),
//                                        shape = CircleShape
//                                    )
//                            }
//
//                        }
//
//                        CyclePhase.Follicular, CyclePhase.Luteal ->
//                            // Case 1.2: chosenMenstruation's cycle phase is CyclePhase.Follicular or CyclePhase.Luteal
//                            Modifier
////                                .outerShadow(
////                                    shadowRadius = 1.dp,
////                                    spreadRadius = 1.dp,
////                                    shadowColor = dynamicCycleThemeColor(cyclePhase = menstruationDayPropertyProperty.cyclePhase),
////                                    shape = CircleShape
////                                )
//                                .border(
//                                    width = 1.dp, color = Color(0xFFACACAC), shape = CircleShape
//                                )
//                                .background(
//                                    color = Color(0xFFF2F2F7), shape = CircleShape
//                                )
//
//                        else -> Modifier
////                            .outerShadow(
////                                shadowRadius = 1.dp,
////                                spreadRadius = 1.dp,
////                                shadowColor = Color(0x3333334D),
////                                shape = CircleShape
////                            )
//                            .background(
//                                color = Color(0xFFF2F2F7), shape = CircleShape
//                            )
//                    }
//                } else {
//                    // Case 2: chosenMenstruationDay is not localDate, users does not choose this day
//                    Modifier
//                        .border(
//                            width = 1.dp,
//                            shape = CircleShape,
//                            color =
//                                // Case 2.1 -  there days are menstruation day or day belongs fertile phase
//                                if (menstruationDayPropertyProperty.cyclePhase == CyclePhase.Menstruation ||
//                                    menstruationDayPropertyProperty.cyclePhase == CyclePhase.Fertile && isRegularCycle ||
//                                    menstruationDayPropertyProperty.cyclePhase == CyclePhase.Fertile && menstruationDayPropertyProperty.isPredictedCycle
//                                ) {
//                                    dynamicCycleThemeColor(cyclePhase = menstruationDayPropertyProperty.cyclePhase)
//                                } else
//                                // Case 2.2 - there days belongs to follicular phase or luteal phase
//                                    Color.Transparent,
//
//                            )
//                        .background(
//                            shape = CircleShape,
//                            color =
//                                //Case 2.1 - there days are menstruation day or day belongs fertile phase
//                                when (menstruationDayPropertyProperty.cyclePhase) {
//                                    CyclePhase.Menstruation -> {
//                                        if (
//                                            (menstruationDayPropertyProperty.isCurrentCycle && localDate < LocalDateUtil.now()
//                                             || (menstruationDayPropertyProperty.isCurrentCycle && localDate == LocalDateUtil.now()))
//                                        ) {
//                                            // Case 2.1.1 - there days belong to current cycle include today & there days before today
//                                            dynamicCycleThemeColor(cyclePhase = menstruationDayPropertyProperty.cyclePhase)
//                                        } else if (!menstruationDayPropertyProperty.isPredictedCycle &&
//                                            menstruationDayPropertyProperty.epochDay <= LocalDateUtil.now()
//                                                .toEpochDay()
//                                        ) {
//                                            // Case 2.1.2 - there days belongs to completed cycle in the past
//                                            dynamicCycleThemeColor(cyclePhase = menstruationDayPropertyProperty.cyclePhase)
//                                        } else {
//                                            // Case 2.1.3 - there days are still menstruation day or fertile phase but they are from predicted cycle
//                                            Color.Transparent
//                                        }
//                                    }
//
//                                    CyclePhase.Fertile -> {
//                                        if (menstruationDayPropertyProperty.isCurrentCycle && isRegularCycle) {
//                                            // Case 2.1.1 - there days belong to current cycle which is regular cycle
//                                            dynamicCycleThemeColor(cyclePhase = menstruationDayPropertyProperty.cyclePhase)
//                                        } else if (
//                                            !menstruationDayPropertyProperty.isPredictedCycle &&
//                                            menstruationDayPropertyProperty.epochDay <= LocalDateUtil.now()
//                                                .toEpochDay() &&
//                                            isRegularCycle
//                                        ) {
//                                            // Case 2.1.2 - there days belongs to completed cycles which are regular cycle & they are before or equal today
//                                            dynamicCycleThemeColor(cyclePhase = menstruationDayPropertyProperty.cyclePhase)
//                                        } else {
//                                            // Case 2.1.3 - there days are still menstruation day or fertile phase but they are from predicted cycle
//                                            Color.Transparent
//                                        }
//                                    }
//
//                                    else
//                                        // Case 2.2 - there days belongs to follicular phase or luteal phase
//                                        -> Color.Transparent
//                                },
//                        )
//                }
//            )
//            .clip(shape = CircleShape)
//            .singleClickable(
//                debounceTime = 1000L,
//                onClick = onClick
//            )
//    )
//    {
//        // Text day of month - Day 01
//        Text(
//            text = if (localDate.dayOfMonth <= 10) localDate.dayOfMonth.toString().padStart(2, '0')
//            else localDate.dayOfMonth.toString(),
//            style = customizedTextStyle(fontSize = 14, fontWeight = 500),
//            color =
//                // Case 1: chosenMenstruationDay equals local date
//                if (chosenMenstruationDay.epochDay == localDate.toEpochDay()) {
//                    val isBeforeOrEqualToToday = localDate < LocalDateUtil.now() || localDate == LocalDateUtil.now()
//                    val isAfterToday = localDate > LocalDateUtil.now()
//                    if (
//                        (menstruationDayPropertyProperty.isCurrentCycle && menstruationDayPropertyProperty.cyclePhase == CyclePhase.Menstruation && isBeforeOrEqualToToday)
//                        ||
//                        (menstruationDayPropertyProperty.isCurrentCycle && menstruationDayPropertyProperty.cyclePhase == CyclePhase.Fertile && isRegularCycle)
//                        ||
//                        (menstruationDayPropertyProperty.isCompletedCycle && menstruationDayPropertyProperty.cyclePhase == CyclePhase.Menstruation)
//                        ||
//                        (menstruationDayPropertyProperty.isCompletedCycle && menstruationDayPropertyProperty.cyclePhase == CyclePhase.Fertile && isRegularCycle)
//
//                    ) {
//                        // Case 1.1.1: chosen menstruation days belongs to current cycle & on Menstruation phase or Fertile phase
//                        Color.White
//                    } else if (
//                        (menstruationDayPropertyProperty.isCurrentCycle && menstruationDayPropertyProperty.cyclePhase == CyclePhase.Menstruation && isAfterToday)
//                        ||
//                        (menstruationDayPropertyProperty.isCurrentCycle && menstruationDayPropertyProperty.cyclePhase == CyclePhase.Follicular)
//                        ||
//                        (menstruationDayPropertyProperty.isCurrentCycle && menstruationDayPropertyProperty.cyclePhase == CyclePhase.Luteal)
//                        ||
//                        (menstruationDayPropertyProperty.isPredictedCycle && menstruationDayPropertyProperty.cyclePhase == CyclePhase.Fertile)
//                    ) {
//                        // Case 1.1.2: chosen menstruation days belongs to current cycle & on Follicular phase or Luteal phase
//                        Color.Black
//                    } else {
//                        // Case 1.1.3: chosen menstruation day's cycle phase is CyclePhase.Menstruation & NOT belongs to current cycle
//                        Color.Black
//                    }
//                } else {
//                    // Case 2: chosenMenstruationDay does not equal local date
//                    when (menstruationDayPropertyProperty.cyclePhase) {
//                        // Case 2.1: there days belong to Menstruation or Fertile
//                        CyclePhase.Menstruation -> {
//                            if (
//                                menstruationDayPropertyProperty.isCurrentCycle && localDate > LocalDateUtil.now()
//
//                            ) {
//                                // Case 2.1.1: there are belong to current cycle but after today
//                                Color.Black
//                            } else if (
//                                (menstruationDayPropertyProperty.isCurrentCycle && localDate < LocalDateUtil.now()
//                                        )
//                                ||
//                                (menstruationDayPropertyProperty.isCurrentCycle && localDate == LocalDateUtil.now()
//                                        )
//                            ) {
//                                // Case 2.1.2 - there days belong to current cycle include today & there days before today
//                                Color.White
//                            } else if (
//                                !menstruationDayPropertyProperty.isPredictedCycle &&
//                                menstruationDayPropertyProperty.epochDay <= LocalDateUtil.now()
//                                    .toEpochDay()
//                            ) {
//                                // Case 2.1.3 - there days belong to completed cycle in the past
//                                Color.White
//                            } else {
//                                // Case 2.1.4: there days belong to predicted cycle
//                                Color.Black
//                            }
//                        }
//
//                        CyclePhase.Fertile -> {
//                            // Case 2.1.1: there are belong to current cycle or completed cycle in the past
//                            if (menstruationDayPropertyProperty.isCurrentCycle && isRegularCycle ||
//                                (!menstruationDayPropertyProperty.isPredictedCycle && localDate < LocalDateUtil.now() && isRegularCycle)
//                            ) {
//                                Color.White
//                            } else if (menstruationDayPropertyProperty.isPredictedCycle) {
//                                Color.Black
//                            } else {
//                                // Case 2.1.2: there are belong to predicted cycle & completed cycle
//                                Color.Black
//                            }
//                        }
//                        // Case 2.2: there days belong to Follicular or Luteal
//                        else -> Color.Black
//                    }
//                },
//            textAlign = TextAlign.Center,
//            modifier = Modifier,
//        )
//    }
//}
//
//@Composable
//fun CoreWeekday(
//    localDate: LocalDate
//) {
//    val weekday by remember(localDate) {
//        derivedStateOf {
//            Weekday.entries.find { it.name == localDate.dayOfWeek.name } ?: Weekday.MONDAY
//        }
//    }
//    Text(
//        text = if (localDate == LocalDateUtil.now()) // if local date is today
//            stringResource(resource = Res.string.today)
//        else
////            localDate.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }.take(3)
//            stringResource(weekday.shortName),
//        style = customizedTextStyle(color = Color.Black),
//        maxLines = 1,
//        textAlign = TextAlign.Center,
//        modifier = Modifier
//            .width(
//                50.dp
//                /** figmaWidthScale*/
//            )
//            .clip(shape = RoundedCornerShape(30.dp))
//            .then(
//                if (localDate == LocalDateUtil.now()) Modifier.background(color = Color.White)
//                else Modifier.background(color = Color.Transparent)
//            )
//            .padding(vertical = 4.dp, horizontal = 5.dp)
//            .basicMarquee(Int.MAX_VALUE)
//    )
//}
//
//@Composable
//fun OvulationDay(
//    enable: Boolean,
//    modifier: Modifier = Modifier,
//) {
//    Column(modifier = modifier) {
//        // icon egg will be shown if it's ovulation day
//        Spacer(modifier = Modifier.height(2.dp))
//        Icon(
//            painter = painterResource(Res.drawable.ic_egg),
//            contentDescription = null,
//            tint = Color(0xFF88C0FF),
//            modifier = Modifier.alpha(alpha = if (enable) 1f else 0f)
//        )
//    }
//
//}
//
//@Preview
//@Composable
//private fun PreviewCoreCell() {
//
//    Row(
//
//        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.background(color = Color.White)
//        ) {
//            CoreWeekday(
//                localDate = LocalDateUtil.now()
//            )
//
//            CoreCell(
//                chosenMenstruationDay = MenstruationDay(
//                    epochDay = LocalDateUtil.now().toEpochDay()
//                ),
//                localDate = LocalDateUtil.now(),
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Menstruation, isCurrentCycle = true
//                ),
//                onClick = {},
//                isRegularCycle = true
//            )
//
//            OvulationDay(
//                enable = true
//            )
//        }
//
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.background(color = Color.White)
//        ) {
//            CoreWeekday(
//                localDate = LocalDateUtil.now().plusDays(1)
//            )
//
//            CoreCell(
//                chosenMenstruationDay = MenstruationDay(
//                    epochDay = LocalDateUtil.now().toEpochDay()
//                ),
//                localDate = LocalDateUtil.now(),
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Follicular, isCurrentCycle = true
//                ),
//                onClick = {},
//                isRegularCycle = true
//            )
//
//            OvulationDay(
//                enable = true
//            )
//        }
//
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.background(color = Color.White)
//        ) {
//            CoreWeekday(
//                localDate = LocalDateUtil.now().plusDays(2)
//            )
//
//            CoreCell(
//                chosenMenstruationDay = MenstruationDay(
//                    epochDay = LocalDateUtil.now().toEpochDay()
//                ),
//                localDate = LocalDateUtil.now(),
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Fertile, isCurrentCycle = true
//                ),
//                onClick = {},
//                isRegularCycle = true
//            )
//
//            OvulationDay(
//                enable = false
//            )
//        }
//
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.background(color = Color.White)
//        ) {
//            CoreWeekday(
//                localDate = LocalDateUtil.now().plusDays(3)
//            )
//
//            CoreCell(
//                chosenMenstruationDay = MenstruationDay(
//                    epochDay = LocalDateUtil.now().toEpochDay()
//                ),
//                localDate = LocalDateUtil.now(),
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Fertile, isCurrentCycle = true, isOvulationDay = true
//                ),
//                onClick = {},
//                isRegularCycle = true
//            )
//
//            OvulationDay(
//                enable = true
//            )
//        }
//
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.background(color = Color.White)
//        ) {
//            CoreWeekday(
//                localDate = LocalDateUtil.now().plusDays(4)
//            )
//
//            CoreCell(
//                chosenMenstruationDay = MenstruationDay(
//                    epochDay = LocalDateUtil.now().toEpochDay()
//                ),
//                localDate = LocalDateUtil.now(),
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Luteal, isCurrentCycle = true, isOvulationDay = true
//                ),
//                onClick = {},
//                isRegularCycle = true
//            )
//
//            OvulationDay(
//                enable = false
//            )
//        }
//    }
//
//
//}