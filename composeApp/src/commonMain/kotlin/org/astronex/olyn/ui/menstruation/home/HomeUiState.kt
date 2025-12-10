package org.astronex.olyn.ui.menstruation.home

import org.astronex.olyn.domain.enums.InformationItem
import org.astronex.olyn.domain.model.Cycle
import org.astronex.olyn.domain.model.MenstruationDay
import org.astronex.olyn.view.model.MenstruationDayProperty

data class HomeUiState (
    val trendingTopics: List<InformationItem> = listOf<InformationItem>(),
    val listOfMenstruationDayProperty: List<MenstruationDayProperty> = emptyList(),

    // there properties below depends on Normal Day that users choose on CollapsedCalendar Calendar
    val menstruationDayProperty: MenstruationDayProperty? = null,
    val menstruationDay: MenstruationDay = MenstruationDay(),
    val currentCycle: Cycle = Cycle(),
//    val symptomDiary: SymptomDiary = SymptomDiary(),

    // home overall content
    val homeOverallContent: Triple<String, String, String> = Triple("", "", ""),
    val listOfCycle: List<Cycle> = emptyList(),

    // there properties below update Expanded Calendar in Calendar screen
    val listOfMenstruationDay: List<MenstruationDay> = emptyList(),
)