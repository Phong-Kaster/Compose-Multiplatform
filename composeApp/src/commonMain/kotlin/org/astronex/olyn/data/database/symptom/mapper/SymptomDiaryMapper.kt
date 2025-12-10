package org.astronex.olyn.data.database.symptom.mapper

import org.astronex.olyn.data.database.symptom.entity.SymptomDiaryEntity
import org.astronex.olyn.domain.enums.Symptom
import org.astronex.olyn.domain.enums.Symptom.Companion.getByName
import org.astronex.olyn.domain.model.SymptomDiary


object SymptomDiaryMapper {
    fun SymptomDiary.toEntity(): SymptomDiaryEntity {
        val outcome = SymptomDiaryEntity (
            epochDay = epochDay,
            weight = weight,
            bodyTemperature = bodyTemperature,
            listOfSymptom = listOfSymptom.joinToString(",") // from list { happy, angry, calm } transform to string "happy, angry, calm"
        )
        return outcome
    }

    fun SymptomDiaryEntity.toModel(): SymptomDiary {
        val listOfSymptom: List<Symptom> =
            if (listOfSymptom.isEmpty()) {
                emptyList()
            } else {
                listOfSymptom.split(",").map { it -> getByName(it) }// from string "happy, angry, calm" transform to list { happy, angry, calm }
            }

        val outcome = SymptomDiary(
            epochDay = epochDay,
            weight = weight,
            bodyTemperature = bodyTemperature,
            listOfSymptom = listOfSymptom
        )
        return outcome
    }
}