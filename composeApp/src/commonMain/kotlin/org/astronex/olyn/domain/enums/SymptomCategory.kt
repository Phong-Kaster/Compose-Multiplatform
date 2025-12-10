package org.astronex.olyn.domain.enums


import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.distressing_feeling_often
import compose_multiplatform.composeapp.generated.resources.exercise
import compose_multiplatform.composeapp.generated.resources.hair
import compose_multiplatform.composeapp.generated.resources.input_your_temperature_to_track_ovulation
import compose_multiplatform.composeapp.generated.resources.input_your_weight_to_track_changes
import compose_multiplatform.composeapp.generated.resources.light_exercise_will_help
import compose_multiplatform.composeapp.generated.resources.medicine
import compose_multiplatform.composeapp.generated.resources.monitor_the_condition_of_your_hair
import compose_multiplatform.composeapp.generated.resources.mood
import compose_multiplatform.composeapp.generated.resources.pain
import compose_multiplatform.composeapp.generated.resources.period
import compose_multiplatform.composeapp.generated.resources.sanitation
import compose_multiplatform.composeapp.generated.resources.sex_life
import compose_multiplatform.composeapp.generated.resources.take_note_of_your_sexual_life
import compose_multiplatform.composeapp.generated.resources.temperature
import compose_multiplatform.composeapp.generated.resources.the_amount_of_blood
import compose_multiplatform.composeapp.generated.resources.the_hygiene_method_also_affects_menstrual_health
import compose_multiplatform.composeapp.generated.resources.the_imbalance_of_hormones_and_brain_chemicals
import compose_multiplatform.composeapp.generated.resources.various_types_of_medication
import compose_multiplatform.composeapp.generated.resources.weight
import org.jetbrains.compose.resources.StringResource
import kotlin.math.ceil

enum class SymptomCategory(
    val text: StringResource = Res.string.period,
     val description: StringResource = Res.string.the_amount_of_blood,
    val listOfSymptom: List<Symptom>
) {
    Period(
        text = Res.string.period,
        description = Res.string.the_amount_of_blood,
        listOfSymptom = listOf(Symptom.Light, Symptom.Medium, Symptom.Heavy, Symptom.SuperHeavy),
    ),

    Mood(
        text = Res.string.mood,
        description = Res.string.the_imbalance_of_hormones_and_brain_chemicals,
        listOfSymptom = listOf(
            Symptom.Happiness,
            Symptom.Calm,
            Symptom.Tiredness,
            Symptom.Moodiness,
            Symptom.MoodSwings,
            Symptom.Depression,
            Symptom.Sadness,
            Symptom.Tension,
            Symptom.Anxiety
        )
    ),

    Pain(
        text = Res.string.pain,
        description = Res.string.distressing_feeling_often,
        listOfSymptom = listOf(
            Symptom.BreastPain,
            Symptom.TroubleFocusing,
            Symptom.Bloating,
            Symptom.Headache,
            Symptom.Nausea,
            Symptom.Diarrhea,
            Symptom.Dizziness,
            Symptom.Vomiting,
            Symptom.Constipation,
            Symptom.Backache,
            Symptom.Cramps
        )
    ),

    SexLife(
        text = Res.string.sex_life,
        description = Res.string.take_note_of_your_sexual_life,
        listOfSymptom = listOf(Symptom.Protected, Symptom.Unprotected)
    ),

    Exercise(
        text = Res.string.exercise,
        description = Res.string.light_exercise_will_help,
        listOfSymptom = listOf(Symptom.Cardio, Symptom.Yoga, Symptom.Pilates, Symptom.Gym, Symptom.Others)
    ),

    Sanitation(
        text = Res.string.sanitation,
        description = Res.string.the_hygiene_method_also_affects_menstrual_health,
        listOfSymptom = listOf(Symptom.Pad, Symptom.Tampon, Symptom.Cup, Symptom.Panty)
    ),

    Hair(
        text = Res.string.hair,
        description = Res.string.monitor_the_condition_of_your_hair,
        listOfSymptom = listOf(Symptom.Good, Symptom.Hairloss, Symptom.Dry, Symptom.Oily)
    ),

    Medicine(
        text = Res.string.medicine,
        description = Res.string.various_types_of_medication,
        listOfSymptom = listOf(Symptom.PainKiller, Symptom.PrenatalVitamin, Symptom.PrescriptionMedication)
    ),

    Weight(
        text = Res.string.weight,
        description = Res.string.input_your_weight_to_track_changes,
        listOfSymptom = emptyList()
    ),

    Temperature(
        text = Res.string.temperature,
        description = Res.string.input_your_temperature_to_track_ovulation,
        listOfSymptom = emptyList()
    ),
    ;

    companion object {

        fun calculateTotalPage(): Int {
            var totalPage = 0
            entries.forEach {
                val totalSymptom = it.listOfSymptom.size
                val requiredPage = ceil(totalSymptom / 6.0).toInt()

                totalPage += requiredPage
            }

            return totalPage
        }

        fun calculateTotalSymptom(symptomCategory: SymptomCategory, mapSymptom: Map<Symptom, Int>): Int {
            var outcome = 0
            mapSymptom.filter {
                symptomCategory.listOfSymptom.contains(it.key)
            }.forEach {
                outcome += it.value
            }

            return outcome
        }
    }
}