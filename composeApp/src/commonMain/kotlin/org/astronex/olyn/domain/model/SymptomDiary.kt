package org.astronex.olyn.domain.model

import androidx.compose.runtime.Stable
import org.astronex.olyn.domain.enums.Symptom
import org.astronex.olyn.util.LocalDateUtil
import org.astronex.olyn.util.LocalDateUtil.toEpochDay


@Stable
data class SymptomDiary(
    val epochDay: Long = LocalDateUtil.now().toEpochDay(),
    val weight: Weight = Weight(),
    val bodyTemperature: BodyTemperature = BodyTemperature(),
    val listOfSymptom: List<Symptom> = emptyList<Symptom>()
) {
    fun hasSymptoms() = listOfSymptom.isNotEmpty() //|| weight != null || bodyTemperature != null

    companion object {
        fun generateFakeSymptomDiary(): SymptomDiary {
            return SymptomDiary(
                epochDay =  LocalDateUtil.now().toEpochDay(),
                weight = Weight.generateFakeWeight(),
                bodyTemperature = BodyTemperature.generateFakeBodyTemperature(),
                listOfSymptom = Symptom.generateFakeListOfSymptom()
            )
        }
    }
}