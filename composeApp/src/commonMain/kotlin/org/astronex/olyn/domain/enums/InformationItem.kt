package org.astronex.olyn.domain.enums

import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource


interface InformationItem {
    val id: Long
    val titleId: StringResource
    val contentId: StringResource
    val thumbnailId: DrawableResource?
    val imageId: DrawableResource?
    val imageDetailId: DrawableResource?
    val name: String
    val sourceNameId: StringResource?
    val linkSourceId: StringResource?
    val displayedArticle: Boolean
}

enum class CycleDefinitionItem(
    override val id: Long,
    override val titleId: StringResource,
    override val contentId: StringResource,
    override val imageId: DrawableResource,
    override val imageDetailId: DrawableResource? = null,
    override val thumbnailId: DrawableResource?,
    override val sourceNameId: StringResource? = null,
    override val linkSourceId: StringResource? = null,
    override val displayedArticle: Boolean = true,
) : InformationItem {

    CycleDefinition1(
        id = 101,
        titleId = Res.string.cycle_definition_1_title,
        contentId = Res.string.cycle_definition_1_content,
        imageId = Res.drawable.cycle_definition_1,
        imageDetailId = Res.drawable.img_cycle_definition_content_1,
        thumbnailId = Res.drawable.cycle_definition_thumbnail_1,
        sourceNameId = Res.string.source_health_matters,
        linkSourceId = Res.string.source_health_matters_link,
    ),

    CycleDefinition2(
        id = 102,
        titleId = Res.string.cycle_definition_2_title,
        contentId = Res.string.cycle_definition_2_content,
        imageId = Res.drawable.cycle_definition_2,
        imageDetailId = Res.drawable.img_cycle_definition_content_2,
        thumbnailId = Res.drawable.cycle_definition_thumbnail_2,
        sourceNameId = Res.string.source_oash,
        linkSourceId = Res.string.source_oash_link,
    ),

    CycleDefinition3(
        id = 103,
        titleId = Res.string.cycle_definition_3_title,
        contentId = Res.string.cycle_definition_3_content,
        imageId = Res.drawable.cycle_definition_3,
        imageDetailId = Res.drawable.img_cycle_definition_content_3,
        thumbnailId = Res.drawable.cycle_definition_thumbnail_3,
        sourceNameId = Res.string.source_oash,
        linkSourceId= Res.string.source_oash_link,
    ),

    CycleDefinition4(
        id = 104,
        titleId = Res.string.cycle_definition_4_title,
        contentId = Res.string.cycle_definition_4_content,
        imageId = Res.drawable.cycle_definition_4,
        imageDetailId = Res.drawable.img_cycle_definition_content_4,
        thumbnailId = Res.drawable.cycle_definition_thumbnail_4,
        sourceNameId = Res.string.source_oash,
        linkSourceId = Res.string.source_oash_link,
    ),

    CycleDefinition5(
        id = 105,
        titleId = Res.string.cycle_definition_5_title,
        contentId = Res.string.cycle_definition_5_content,
        imageId = Res.drawable.cycle_definition_5,
        imageDetailId = Res.drawable.img_cycle_definition_content_5,
        thumbnailId = Res.drawable.cycle_definition_thumbnail_5,
        sourceNameId = Res.string.source_oash,
        linkSourceId = Res.string.source_oash_link,
    ),

    CycleDefinition6(
        id = 106,
        titleId = Res.string.cycle_definition_6_title,
        contentId = Res.string.cycle_definition_6_content,
        imageId = Res.drawable.cycle_definition_6,
        imageDetailId = Res.drawable.img_cycle_definition_content_6,
        thumbnailId = Res.drawable.cycle_definition_thumbnail_6,
        sourceNameId = Res.string.source_oash,
        linkSourceId = Res.string.source_oash_link,
    ),

    CycleDefinition7(
        id = 107,
        titleId = Res.string.cycle_definition_7_title,
        contentId = Res.string.cycle_definition_7_content,
        imageId = Res.drawable.cycle_definition_7,
        thumbnailId = Res.drawable.cycle_definition_thumbnail_7,
        sourceNameId = Res.string.source_harvard_health,
        linkSourceId = Res.string.source_harvard_health_link,
        displayedArticle = false,
    ),

    ;

    companion object {
        fun generateFakeArticles(): List<InformationItem> {
            return CycleDefinitionItem.entries
        }
    }
}

enum class MenstrualPhaseItem(
    override val id: Long,
    override val titleId: StringResource,
    override val contentId: StringResource,
    override val imageId: DrawableResource,
    override val imageDetailId: DrawableResource? = null,
    override val thumbnailId: DrawableResource?,
    override val sourceNameId: StringResource? = null,
    override val linkSourceId: StringResource? = null,
    override val displayedArticle: Boolean = true,
) : InformationItem {
    MenstrualPhase1(
        id = 201,
        titleId = Res.string.menstrual_phase_1_title,
        contentId = Res.string.menstrual_phase_1_content,
        imageId = Res.drawable.menstrual_phase_1,
        imageDetailId = Res.drawable.img_menstrual_phase_content_1,
        thumbnailId = Res.drawable.menstrual_phase_thumbnail_1,
        sourceNameId = Res.string.source_nhs,
        linkSourceId = Res.string.source_nhs_link,
    ),

    MenstrualPhase2(
        id = 202,
        titleId = Res.string.menstrual_phase_2_title,
        contentId = Res.string.menstrual_phase_2_content,
        imageId = Res.drawable.menstrual_phase_2,
        imageDetailId = Res.drawable.img_menstrual_phase_content_2,
        thumbnailId = Res.drawable.menstrual_phase_thumbnail_2,
        sourceNameId = Res.string.source_nhs,
        linkSourceId = Res.string.source_nhs_link,
    ),

    MenstrualPhase3(
        id = 203,
        titleId = Res.string.menstrual_phase_3_title,
        contentId = Res.string.menstrual_phase_3_content,
        imageId = Res.drawable.menstrual_phase_3,
        thumbnailId = Res.drawable.menstrual_phase_thumbnail_3,
        sourceNameId = Res.string.source_nhs,
        linkSourceId = Res.string.source_nhs_link,
    ),

    MenstrualPhase4(
        id = 204,
        titleId = Res.string.menstrual_phase_4_title,
        contentId = Res.string.menstrual_phase_4_content,
        imageId = Res.drawable.menstrual_phase_4,
        imageDetailId = Res.drawable.img_menstrual_phase_content_4,
        thumbnailId = Res.drawable.menstrual_phase_thumbnail_4,
        sourceNameId = Res.string.source_cleveland_clinic,
        linkSourceId = Res.string.source_cleveland_clinic_link,
    ),

    MenstrualPhase5(
        id = 205,
        titleId = Res.string.menstrual_phase_5_title,
        contentId = Res.string.menstrual_phase_5_content,
        imageId = Res.drawable.menstrual_phase_5,
        imageDetailId = Res.drawable.img_menstrual_phase_content_5,
        thumbnailId = Res.drawable.menstrual_phase_thumbnail_5,
        sourceNameId = Res.string.source_utah,
        linkSourceId = Res.string.source_utah_link,
        displayedArticle = false,
    ),

    MenstrualPhase6(
        id = 206,
        titleId = Res.string.menstrual_phase_6_title,
        contentId = Res.string.menstrual_phase_6_content,
        imageId = Res.drawable.menstrual_phase_6,
        thumbnailId = Res.drawable.menstrual_phase_thumbnail_6,
        sourceNameId = Res.string.source_utah,
        linkSourceId = Res.string.source_utah_link,
        displayedArticle = false,
    ),
}

enum class FollicularPhaseItem(
    override val id: Long,
    override val titleId: StringResource,
    override val contentId: StringResource,
    override val imageId: DrawableResource,
    override val imageDetailId: DrawableResource? = null,
    override val thumbnailId: DrawableResource?,
    override val sourceNameId: StringResource? = null,
    override val linkSourceId: StringResource? = null,
    override val displayedArticle: Boolean = true,
) : InformationItem {
    FollicularPhase1(
        id = 301,
        titleId = Res.string.follicular_phase_1_title,
        contentId = Res.string.follicular_phase_1_content,
        imageId = Res.drawable.follicular_phase_item_1,
        imageDetailId = Res.drawable.img_follicular_phase_content_1,
        thumbnailId = Res.drawable.follicular_phase_thumbnail_1,
        sourceNameId = Res.string.source_cleveland_clinic,
        linkSourceId = Res.string.source_cleveland_clinic_link,
    ),

    FollicularPhase2(
        id = 302,
        titleId = Res.string.follicular_phase_2_title,
        contentId = Res.string.follicular_phase_2_content,
        imageId = Res.drawable.follicular_phase_item_2,
        thumbnailId = Res.drawable.follicular_phase_thumbnail_2,
        sourceNameId = Res.string.source_forbes,
        linkSourceId = Res.string.source_forbes_link,
        displayedArticle = false,
    ),

    FollicularPhase3(
        id = 303,
        titleId = Res.string.follicular_phase_3_title,
        contentId = Res.string.follicular_phase_3_content,
        imageId = Res.drawable.follicular_phase_item_3,
        thumbnailId = Res.drawable.follicular_phase_thumbnail_3,
        sourceNameId = Res.string.source_obria,
        linkSourceId = Res.string.source_obria_link,
        displayedArticle = false,
    ),
}

enum class OvulatoryPhaseItem(
    override val id: Long,
    override val titleId: StringResource,
    override val contentId: StringResource,
    override val imageId: DrawableResource,
    override val imageDetailId: DrawableResource? = null,
    override val thumbnailId: DrawableResource?,
    override val sourceNameId: StringResource? = null,
    override val linkSourceId: StringResource? = null,
    override val displayedArticle: Boolean = true,
) : InformationItem {
    OvulatoryPhase1(
        id = 401,
        titleId = Res.string.ovulatory_phase_1_title,
        contentId = Res.string.ovulatory_phase_1_content,
        imageId = Res.drawable.ovulatory_phase_item_1,
        imageDetailId = Res.drawable.img_ovulatory_phase_content_1,
        thumbnailId = Res.drawable.ovulatory_phase_thumbnail_1,
        sourceNameId = Res.string.source_cleveland_clinic,
        linkSourceId = Res.string.source_cleveland_clinic_link,
    ),

    OvulatoryPhase2(
        id = 402,
        titleId = Res.string.ovulatory_phase_2_title,
        contentId = Res.string.ovulatory_phase_2_content,
        imageId = Res.drawable.ovulatory_phase_item_2,
        thumbnailId = Res.drawable.ovulatory_phase_thumbnail_2,
        sourceNameId = Res.string.source_health_woman,
        linkSourceId = Res.string.source_health_woman_link,
        displayedArticle = false,
    ),

    OvulatoryPhase3(
        id = 403,
        titleId = Res.string.ovulatory_phase_3_title,
        contentId = Res.string.ovulatory_phase_3_content,
        imageId = Res.drawable.ovulatory_phase_item_3,
        thumbnailId = Res.drawable.ovulatory_phase_thumbnail_3,
        sourceNameId = Res.string.source_cleveland_clinic,
        linkSourceId = Res.string.source_cleveland_clinic_link,
        displayedArticle = false,
    ),

    OvulatoryPhase4(
        id = 404,
        titleId = Res.string.ovulatory_phase_4_title,
        contentId = Res.string.ovulatory_phase_4_content,
        imageId = Res.drawable.ovulatory_phase_item_4,
        thumbnailId = Res.drawable.ovulatory_phase_thumbnail_4,
        sourceNameId = Res.string.source_forbes,
        linkSourceId = Res.string.source_forbes_link,
        displayedArticle = false,
    ),
}

enum class LutealPhaseItem(
    override val id: Long,
    override val titleId: StringResource,
    override val contentId: StringResource,
    override val imageId: DrawableResource,
    override val imageDetailId: DrawableResource? = null,
    override val thumbnailId: DrawableResource?,
    override val sourceNameId: StringResource? = null,
    override val linkSourceId: StringResource? = null,
    override val displayedArticle: Boolean = true,
) : InformationItem {
    LutealPhase1(
        id = 501,
        titleId = Res.string.luteal_phase_1_title,
        contentId = Res.string.luteal_phase_1_content,
        imageId = Res.drawable.luteal_phase_item_1,
        imageDetailId = Res.drawable.img_luteal_phase_content_1,
        thumbnailId = Res.drawable.luteal_phase_thumbail_1,
        sourceNameId = Res.string.source_cleveland_clinic,
        linkSourceId = Res.string.source_cleveland_clinic_link,
    ),

    LutealPhase2(
        id = 502,
        titleId = Res.string.luteal_phase_2_title,
        contentId = Res.string.luteal_phase_2_content,
        imageId = Res.drawable.luteal_phase_item_2,
        thumbnailId = Res.drawable.luteal_phase_thumbail_2,
        sourceNameId = Res.string.source_your_fertility,
        linkSourceId = Res.string.source_your_fertility_link,
        displayedArticle = false,
    ),

    LutealPhase3(
        id = 503,
        titleId = Res.string.luteal_phase_3_title,
        contentId = Res.string.luteal_phase_3_content,
        imageId = Res.drawable.luteal_phase_item_3,
        thumbnailId = Res.drawable.luteal_phase_thumbail_3,
        sourceNameId = Res.string.source_oash,
        linkSourceId = Res.string.source_oash_link,
        displayedArticle = false,
    ),

    LutealPhase4(
        id = 504,
        titleId = Res.string.luteal_phase_4_title,
        contentId = Res.string.luteal_phase_4_content,
        imageId = Res.drawable.luteal_phase_item_4,
        thumbnailId = Res.drawable.luteal_phase_thumbail_4,
        sourceNameId = Res.string.source_ella_one,
        linkSourceId = Res.string.source_ella_one_link,
        displayedArticle = false,
    ),
}

enum class HealthProblemItem(
    override val id: Long,
    override val titleId: StringResource,
    override val contentId: StringResource,
    override val imageId: DrawableResource,
    override val imageDetailId: DrawableResource? = null,
    override val thumbnailId: DrawableResource?,
    override val sourceNameId: StringResource? = null,
    override val linkSourceId: StringResource? = null,
    override val displayedArticle: Boolean = true,
) : InformationItem {
    HealthProblem1(
        id = 601,
        titleId = Res.string.health_problem_1_title,
        contentId = Res.string.health_problem_1_content,
        imageId = Res.drawable.health_problem_item_1,
        thumbnailId =Res.drawable.health_problem_thumbnail_1,
        sourceNameId = Res.string.source_oash,
        linkSourceId = Res.string.source_oash_link,
    )
}

//enum class BirthControlItem(
//    override val id: Long,
//    override val titleId: Int,
//    override val contentId: Int,
//    override val imageId: Int,
//    override val thumbnailId: Int?,
//    override val sourceNameId: Int? = null,
//    override val linkSourceId: Int? = null,
//    override val displayedArticle: Boolean = true,
//) : InformationItem {
//    BirthControl1(
//        id = 701,
//        Res.string.birth_control_1_title,
//        Res.string.birth_control_1_content,
//        Res.drawable.birth_control_item_1,
//        Res.drawable.birth_control_thumbnail_1,
//        Res.string.birth_control_1_source_link
//    ),
//    BirthControl2(
//        id = 702,
//        Res.string.birth_control_2_title,
//        Res.string.birth_control_2_content,
//        Res.drawable.birth_control_item_2,
//        Res.drawable.birth_control_thumbnail_2,
//        Res.string.birth_control_2_source_link
//    ),
//    BirthControl3(
//        id = 703,
//        Res.string.birth_control_3_title,
//        Res.string.birth_control_3_content,
//        Res.drawable.birth_control_item_3,
//        Res.drawable.birth_control_thumbnail_3,
//        Res.string.birth_control_3_source_link
//    ),
//}
//
//
//enum class PregnancyItem(
//    override val id: Long,
//    override val titleId: Int,
//    override val contentId: Int,
//    override val imageId: Int,
//    override val thumbnailId: Int?,
//    override val sourceNameId: Int? = null,
//    override val linkSourceId: Int? = null,
//    override val displayedArticle: Boolean = true,
//) : InformationItem {
//    Pregnancy1(
//        id = 801,
//        Res.string.pregnancy_1_title,
//        Res.string.pregnancy_1_content,
//        Res.drawable.pregnancy_item_1,
//        Res.drawable.pregnancy_thumbnail_1,
//        Res.string.pregnancy_1_source_link
//    ),
//    Pregnancy2(
//        id = 802,
//        Res.string.pregnancy_2_title,
//        Res.string.pregnancy_2_content,
//        Res.drawable.pregnancy_item_2,
//        Res.drawable.pregnancy_thumbnail_2,
//        Res.string.pregnancy_2_source_link
//    ),
//    Pregnancy3(
//        id = 803,
//        Res.string.pregnancy_3_title,
//        Res.string.pregnancy_3_content,
//        Res.drawable.pregnancy_item_3,
//        Res.drawable.pregnancy_thumbnail_3,
//        Res.string.pregnancy_3_source_link
//    ),
//}

enum class CyclePhaseItem(
    override val id: Long,
    override val titleId: StringResource,
    override val contentId: StringResource,
    override val thumbnailId: DrawableResource? = null,
    override val imageId: DrawableResource? = null,
    override val imageDetailId: DrawableResource? = null,
    override val sourceNameId: StringResource? = null,
    override val linkSourceId: StringResource? = null,
    override val displayedArticle: Boolean = true,
) : InformationItem {

    Menstruation(
        id = 901,
        titleId = Res.string.menstrual_phase,
        contentId = Res.string.menstrual_phase_content,
        thumbnailId = null,
        imageId = null,
        imageDetailId = null,
        sourceNameId = Res.string.source_oash,
        linkSourceId = Res.string.source_oash_link
    ),

    Follicular(
        id = 902,
        titleId = Res.string.the_follicular_phase,
        contentId = Res.string.the_follicular_phase_content,
        thumbnailId = null,
        imageId = null,
        imageDetailId = null,
        sourceNameId = Res.string.source_cleveland_clinic,
        linkSourceId = Res.string.source_cleveland_clinic_link
    ),

    Fertile(
        id = 903,
        titleId = Res.string.fertile_day_ovulation,
        contentId = Res.string.fertile_day_ovulation_content,
        thumbnailId = null,
        imageId = null,
        imageDetailId = null,
        sourceNameId = Res.string.source_cleveland_clinic,
        linkSourceId = Res.string.source_cleveland_clinic_link
    ),

    Luteal(
        id = 904,
        titleId = Res.string.luteal_phase,
        contentId = Res.string.luteal_phase_content,
        thumbnailId = null,
        imageId = null,
        imageDetailId = null,
        sourceNameId = Res.string.source_cleveland_clinic,
        linkSourceId = Res.string.source_cleveland_clinic_link
    ),
}
