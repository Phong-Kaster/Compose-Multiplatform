package org.astronex.olyn.domain.enums

import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.anxiety
import compose_multiplatform.composeapp.generated.resources.app_name
import compose_multiplatform.composeapp.generated.resources.backache
import compose_multiplatform.composeapp.generated.resources.bloating
import compose_multiplatform.composeapp.generated.resources.breast_pain
import compose_multiplatform.composeapp.generated.resources.calm
import compose_multiplatform.composeapp.generated.resources.cardio
import compose_multiplatform.composeapp.generated.resources.constipation
import compose_multiplatform.composeapp.generated.resources.cramps
import compose_multiplatform.composeapp.generated.resources.cup
import compose_multiplatform.composeapp.generated.resources.depression
import compose_multiplatform.composeapp.generated.resources.diarrhea
import compose_multiplatform.composeapp.generated.resources.dizziness
import compose_multiplatform.composeapp.generated.resources.dry
import compose_multiplatform.composeapp.generated.resources.good
import compose_multiplatform.composeapp.generated.resources.gym
import compose_multiplatform.composeapp.generated.resources.hairloss
import compose_multiplatform.composeapp.generated.resources.happiness
import compose_multiplatform.composeapp.generated.resources.headache
import compose_multiplatform.composeapp.generated.resources.heavy
import compose_multiplatform.composeapp.generated.resources.img_anxiety
import compose_multiplatform.composeapp.generated.resources.img_back_pain
import compose_multiplatform.composeapp.generated.resources.img_bloating
import compose_multiplatform.composeapp.generated.resources.img_breast_pain
import compose_multiplatform.composeapp.generated.resources.img_calm
import compose_multiplatform.composeapp.generated.resources.img_cardio
import compose_multiplatform.composeapp.generated.resources.img_constipation
import compose_multiplatform.composeapp.generated.resources.img_cramps
import compose_multiplatform.composeapp.generated.resources.img_cup
import compose_multiplatform.composeapp.generated.resources.img_depression
import compose_multiplatform.composeapp.generated.resources.img_diarrhea
import compose_multiplatform.composeapp.generated.resources.img_dizzy
import compose_multiplatform.composeapp.generated.resources.img_dry
import compose_multiplatform.composeapp.generated.resources.img_good
import compose_multiplatform.composeapp.generated.resources.img_gym
import compose_multiplatform.composeapp.generated.resources.img_hairloss
import compose_multiplatform.composeapp.generated.resources.img_happiness
import compose_multiplatform.composeapp.generated.resources.img_headache
import compose_multiplatform.composeapp.generated.resources.img_heavy_blood
import compose_multiplatform.composeapp.generated.resources.img_light_blood
import compose_multiplatform.composeapp.generated.resources.img_medium_blood
import compose_multiplatform.composeapp.generated.resources.img_mood_swings
import compose_multiplatform.composeapp.generated.resources.img_moodiness
import compose_multiplatform.composeapp.generated.resources.img_nausea
import compose_multiplatform.composeapp.generated.resources.img_none
import compose_multiplatform.composeapp.generated.resources.img_oily
import compose_multiplatform.composeapp.generated.resources.img_others
import compose_multiplatform.composeapp.generated.resources.img_pad
import compose_multiplatform.composeapp.generated.resources.img_painkiller
import compose_multiplatform.composeapp.generated.resources.img_panty
import compose_multiplatform.composeapp.generated.resources.img_pilates
import compose_multiplatform.composeapp.generated.resources.img_precription_medicattion
import compose_multiplatform.composeapp.generated.resources.img_prenatal_vitamin
import compose_multiplatform.composeapp.generated.resources.img_protected
import compose_multiplatform.composeapp.generated.resources.img_sadness
import compose_multiplatform.composeapp.generated.resources.img_super_blood
import compose_multiplatform.composeapp.generated.resources.img_tampon
import compose_multiplatform.composeapp.generated.resources.img_tension
import compose_multiplatform.composeapp.generated.resources.img_tiredness
import compose_multiplatform.composeapp.generated.resources.img_trouble_focusing
import compose_multiplatform.composeapp.generated.resources.img_unprotected
import compose_multiplatform.composeapp.generated.resources.img_vomit
import compose_multiplatform.composeapp.generated.resources.img_yoga
import compose_multiplatform.composeapp.generated.resources.light
import compose_multiplatform.composeapp.generated.resources.medium
import compose_multiplatform.composeapp.generated.resources.mood_swings
import compose_multiplatform.composeapp.generated.resources.moodiness
import compose_multiplatform.composeapp.generated.resources.nausea
import compose_multiplatform.composeapp.generated.resources.none
import compose_multiplatform.composeapp.generated.resources.oily
import compose_multiplatform.composeapp.generated.resources.others
import compose_multiplatform.composeapp.generated.resources.pad
import compose_multiplatform.composeapp.generated.resources.painkiller
import compose_multiplatform.composeapp.generated.resources.panty
import compose_multiplatform.composeapp.generated.resources.pilates
import compose_multiplatform.composeapp.generated.resources.precription_medicattion
import compose_multiplatform.composeapp.generated.resources.prenatal_vitamin
import compose_multiplatform.composeapp.generated.resources.protected_symptom
import compose_multiplatform.composeapp.generated.resources.sadness
import compose_multiplatform.composeapp.generated.resources.super_heavy
import compose_multiplatform.composeapp.generated.resources.tampon
import compose_multiplatform.composeapp.generated.resources.tension
import compose_multiplatform.composeapp.generated.resources.tiredness
import compose_multiplatform.composeapp.generated.resources.trouble_focusing
import compose_multiplatform.composeapp.generated.resources.unprotected_symptom
import compose_multiplatform.composeapp.generated.resources.vomiting
import compose_multiplatform.composeapp.generated.resources.yoga
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource




enum class Symptom(
    val text: StringResource = Res.string.app_name,
    val photo: DrawableResource = Res.drawable.img_light_blood
) {
    /**Period Category */
    Light(Res.string.light, Res.drawable.img_light_blood),
    Medium(Res.string.medium, Res.drawable.img_medium_blood),
    Heavy(Res.string.heavy, Res.drawable.img_heavy_blood),
    SuperHeavy(Res.string.super_heavy, Res.drawable.img_super_blood),

    /** Mood Category */
    Happiness(Res.string.happiness, Res.drawable.img_happiness),
    Calm(Res.string.calm, Res.drawable.img_calm),
    Tiredness(Res.string.tiredness, Res.drawable.img_tiredness),
    Moodiness(Res.string.moodiness, Res.drawable.img_moodiness),
    MoodSwings(Res.string.mood_swings, Res.drawable.img_mood_swings),
    Depression(Res.string.depression, Res.drawable.img_depression),
    Sadness(Res.string.sadness, Res.drawable.img_sadness),
    Tension(Res.string.tension, Res.drawable.img_tension),
    Anxiety(Res.string.anxiety, Res.drawable.img_anxiety),

    /** Pain */
    BreastPain(Res.string.breast_pain, Res.drawable.img_breast_pain),
    TroubleFocusing(Res.string.trouble_focusing, Res.drawable.img_trouble_focusing),
    Bloating(Res.string.bloating, Res.drawable.img_bloating),
    Headache(Res.string.headache, Res.drawable.img_headache),
    Nausea(Res.string.nausea, Res.drawable.img_nausea),
    Diarrhea(Res.string.diarrhea, Res.drawable.img_diarrhea),
    Dizziness(Res.string.dizziness, Res.drawable.img_dizzy),
    Vomiting(Res.string.vomiting, Res.drawable.img_vomit),
    Constipation(Res.string.constipation, Res.drawable.img_constipation),
    Backache(Res.string.backache, Res.drawable.img_back_pain),
    Cramps(Res.string.cramps, Res.drawable.img_cramps),

    /** Sex Life */
    Protected(Res.string.protected_symptom, Res.drawable.img_protected),
    Unprotected(Res.string.unprotected_symptom, Res.drawable.img_unprotected),

    /** Exercise */
    Cardio(Res.string.cardio, Res.drawable.img_cardio),
    Yoga(Res.string.yoga, Res.drawable.img_yoga),
    Pilates(Res.string.pilates, Res.drawable.img_pilates),
    Gym(Res.string.gym, Res.drawable.img_gym),
    Others(Res.string.others, Res.drawable.img_others),

    /** Sanitation */
    Pad(Res.string.pad, Res.drawable.img_pad),
    Tampon(Res.string.tampon, Res.drawable.img_tampon),
    Cup(Res.string.cup, Res.drawable.img_cup),
    Panty(Res.string.panty, Res.drawable.img_panty),

    /** Hair */
    Good(Res.string.good, Res.drawable.img_good),
    Hairloss(Res.string.hairloss, Res.drawable.img_hairloss),
    Dry(Res.string.dry, Res.drawable.img_dry),
    Oily(Res.string.oily, Res.drawable.img_oily),

    /** Medicine */
    PainKiller(Res.string.painkiller, Res.drawable.img_painkiller),
    PrenatalVitamin(Res.string.prenatal_vitamin, Res.drawable.img_prenatal_vitamin),
    PrescriptionMedication(
        Res.string.precription_medicattion,
        Res.drawable.img_precription_medicattion
    ),

    /** None */
    None(text = Res.string.none, photo = Res.drawable.img_none),
    ;

    companion object {
        fun getByName(name: String): Symptom {
            return entries.find { it.name == name } ?: Happiness
        }

        fun generateFakeListOfSymptom(): List<Symptom> {
            return entries.shuffled().take(10)
        }
    }
}
