//package org.astronex.olyn.view.calender.component
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import kotlinx.datetime.LocalDate
//import org.astronex.olyn.domain.enums.CyclePhase
//import org.astronex.olyn.domain.model.MenstruationDay
//import org.astronex.olyn.util.LocalDateUtil
//import org.astronex.olyn.view.model.MenstruationDayProperty
//import org.jetbrains.compose.ui.tooling.preview.Preview
//
//@Composable
//fun CollapsedDayCell(
//    menstruationDayPropertyProperty: MenstruationDayProperty,
//    localDate: LocalDate,
//    chosenMenstruationDay: MenstruationDay,
//    onClick: () -> Unit,
//    isRegularCycle: Boolean = true,
//    modifier: Modifier = Modifier
//) {
//    Column(
//        verticalArrangement = Arrangement.spacedBy(
//            space = 0.dp,
//            alignment = Alignment.CenterVertically
//        ),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier,
//    ) {
//        // Text Weekday - Monday (Today)
//        CoreWeekday(localDate = localDate)
//
//        Spacer(modifier = Modifier.height(4.dp))
//
//        CoreCell(
//            chosenMenstruationDay = chosenMenstruationDay,
//            localDate = localDate,
//            menstruationDayPropertyProperty = menstruationDayPropertyProperty,
//            onClick = onClick,
//            isRegularCycle = isRegularCycle,
//        )
//
//
//        // icon egg will be shown if it's ovulation day and this cycle must be regular
//        OvulationDay(
//            enable = (menstruationDayPropertyProperty.cyclePhase == CyclePhase.Fertile && menstruationDayPropertyProperty.isOvulationDay && isRegularCycle) ||
//                    (menstruationDayPropertyProperty.cyclePhase == CyclePhase.Fertile && menstruationDayPropertyProperty.isOvulationDay && menstruationDayPropertyProperty.isPredictedCycle)
//        )
//    }
//}
//
//
//@Preview
//@Composable
//private fun PreviewNormalDayCell() {
//    Column(
//        verticalArrangement = Arrangement.spacedBy(10.dp),
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(
//                color = Color.DarkGray
//            )
//    ) {
//        Text(
//            text = "When users choose a day, it will be highlighted.",
//            color = Color.White,
//        )
//        Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            CollapsedDayCell(
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Menstruation,
//                    isMenstruationDay = true
//                ),
//                localDate = LocalDateUtil.now(),
//                chosenMenstruationDay = MenstruationDay(),
//                onClick = {},
//                modifier = Modifier
//            )
//
//            CollapsedDayCell(
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Follicular,
//                    isMenstruationDay = true
//                ),
//                localDate = LocalDateUtil.now(),
//                chosenMenstruationDay = MenstruationDay(),
//                onClick = {},
//                modifier = Modifier
//            )
//
//            CollapsedDayCell(
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Fertile,
//                    isOvulationDay = true
//                ),
//                localDate = LocalDateUtil.now(),
//                chosenMenstruationDay = MenstruationDay(),
//                onClick = {},
//                modifier = Modifier
//            )
//
//
//            CollapsedDayCell(
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Fertile,
//                    isOvulationDay = false
//                ),
//                localDate = LocalDateUtil.now(),
//                chosenMenstruationDay = MenstruationDay(),
//                onClick = {},
//                modifier = Modifier
//            )
//
//            CollapsedDayCell(
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Luteal,
//                ),
//                localDate = LocalDateUtil.now(),
//                chosenMenstruationDay = MenstruationDay(),
//                onClick = {},
//                modifier = Modifier
//            )
//        }
//
//        Text(
//            text = "When users doest not choose any day in current cycle",
//            color = Color.White,
//        )
//        Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            CollapsedDayCell(
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Menstruation,
//                    isMenstruationDay = true
//                ),
//                localDate = LocalDateUtil.now(),
//                chosenMenstruationDay = MenstruationDay(
//                    epochDay = LocalDateUtil.now().plusDays(1).toEpochDay()
//                ),
//                onClick = {},
//                modifier = Modifier
//            )
//
//            CollapsedDayCell(
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Follicular,
//                    isMenstruationDay = true
//                ),
//                localDate = LocalDateUtil.now(),
//                chosenMenstruationDay = MenstruationDay(
//                    epochDay = LocalDateUtil.now().plusDays(1).toEpochDay()
//                ),
//                onClick = {},
//                modifier = Modifier
//            )
//
//            CollapsedDayCell(
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Fertile,
//                    isOvulationDay = true
//                ),
//                localDate = LocalDateUtil.now(),
//                chosenMenstruationDay = MenstruationDay(
//                    epochDay = LocalDateUtil.now().plusDays(1).toEpochDay()
//                ),
//                onClick = {},
//                modifier = Modifier
//            )
//
//            CollapsedDayCell(
//
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Fertile,
//                    isOvulationDay = false
//                ),
//                localDate = LocalDateUtil.now(),
//                chosenMenstruationDay = MenstruationDay(
//                    epochDay = LocalDateUtil.now().plusDays(1).toEpochDay()
//                ),
//                onClick = {},
//                modifier = Modifier
//            )
//
//            CollapsedDayCell(
//
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Luteal,
//                ),
//                localDate = LocalDateUtil.now(),
//                chosenMenstruationDay = MenstruationDay(
//                    epochDay = LocalDateUtil.now().plusDays(1).toEpochDay()
//                ),
//                onClick = {},
//                modifier = Modifier
//            )
//        }
//
//
//        Text(
//            text = "When users doest not choose any day in predicted cycle",
//            color = Color.White,
//        )
//        Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            CollapsedDayCell(
//
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Menstruation,
//                    isMenstruationDay = true,
//                    isPredictedCycle = true,
//                ),
//                localDate = LocalDateUtil.now(),
//                chosenMenstruationDay = MenstruationDay(
//                    epochDay = LocalDateUtil.now().plusDays(1).toEpochDay()
//                ),
//                onClick = {},
//                modifier = Modifier
//            )
//
//            CollapsedDayCell(
//
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Follicular,
//                    isMenstruationDay = true,
//                    isPredictedCycle = true,
//                ),
//                localDate = LocalDateUtil.now(),
//                chosenMenstruationDay = MenstruationDay(
//                    epochDay = LocalDateUtil.now().plusDays(1).toEpochDay()
//                ),
//                onClick = {},
//                modifier = Modifier
//            )
//
//            CollapsedDayCell(
//
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Fertile,
//                    isOvulationDay = true,
//                    isPredictedCycle = true,
//                ),
//                localDate = LocalDateUtil.now(),
//                chosenMenstruationDay = MenstruationDay(
//                    epochDay = LocalDateUtil.now().plusDays(1).toEpochDay()
//                ),
//                onClick = {},
//                modifier = Modifier
//            )
//
//            CollapsedDayCell(
//
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Fertile,
//                    isOvulationDay = false,
//                    isPredictedCycle = true,
//                ),
//                localDate = LocalDateUtil.now(),
//                chosenMenstruationDay = MenstruationDay(
//                    epochDay = LocalDateUtil.now().plusDays(1).toEpochDay()
//                ),
//                onClick = {},
//                modifier = Modifier
//            )
//
//            CollapsedDayCell(
//
//                menstruationDayPropertyProperty = MenstruationDayProperty(
//                    cyclePhase = CyclePhase.Luteal,
//                    isPredictedCycle = true,
//                ),
//                localDate = LocalDateUtil.now(),
//                chosenMenstruationDay = MenstruationDay(
//                    epochDay = LocalDateUtil.now().plusDays(1).toEpochDay()
//                ),
//                onClick = {},
//                modifier = Modifier
//            )
//        }
//    }
//
//
//}