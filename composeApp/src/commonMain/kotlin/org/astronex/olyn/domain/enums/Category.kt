package org.astronex.olyn.domain.enums

import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.cycle_definition
import compose_multiplatform.composeapp.generated.resources.follicular_phase
import compose_multiplatform.composeapp.generated.resources.health_problem
import compose_multiplatform.composeapp.generated.resources.information_menstruation
import compose_multiplatform.composeapp.generated.resources.luteal_phase
import compose_multiplatform.composeapp.generated.resources.menstrual_phase
import compose_multiplatform.composeapp.generated.resources.ovulatory_phase
import org.jetbrains.compose.resources.StringResource

interface InformationItemCategory {
    val stringId: StringResource
    val items: List<InformationItem>
    val displayedArticle: Boolean
}

enum class InformationTab(
    val stringId: StringResource,
    val categories: List<Category>,
) {
    Menstruation(
        Res.string.information_menstruation,
        listOf(
            Category.CycleDefinition,
            Category.MenstrualPhase,
            Category.FollicularPhase,
            Category.OvulatoryPhase,
            Category.LutealPhase,
            Category.HeathProblem,
        ),
    ),

//    Pregnancy(
//        Res.string.information_pregnancy,
//        listOf(InformationCategory.BirthControl, InformationCategory.Pregnancy),
//    )
}

enum class Category(
    override val stringId: StringResource,
    override val items: List<InformationItem>,
    override val displayedArticle: Boolean = true,
) : InformationItemCategory {

    CycleDefinition(
        Res.string.cycle_definition,
        CycleDefinitionItem.entries
    ),

    MenstrualPhase(
        Res.string.menstrual_phase,
        MenstrualPhaseItem.entries
    ),

    FollicularPhase(
        Res.string.follicular_phase,
        FollicularPhaseItem.entries
    ),

    OvulatoryPhase(
        Res.string.ovulatory_phase,
        OvulatoryPhaseItem.entries
    ),

    LutealPhase(
        Res.string.luteal_phase,
        LutealPhaseItem.entries
    ),

    HeathProblem(
        Res.string.health_problem,
        HealthProblemItem.entries
    ),

//    BirthControl(
//        Res.string.information_birth_control,
//        BirthControlItem.entries
//    ),
//
//    Pregnancy(
//        Res.string.information_pregnancy,
//        PregnancyItem.entries
//    ),
//
    CyclePhase(
        Res.string.cycle_definition,
        CyclePhaseItem.entries,
        displayedArticle = false,
    ),
//
//    Notification(
//        Res.string.empty_string,
//        CyclePhaseItem.entries,
//        displayedArticle = false,
//    )
}