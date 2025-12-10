package org.astronex.olyn.data.database.symptom.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import org.astronex.olyn.domain.model.BodyTemperature
import org.astronex.olyn.domain.model.Weight


class SymptomConverter {

    private val json = Json(
        from = Json.Default,
        builderAction = { ignoreUnknownKeys = true })

    @TypeConverter
    fun weightToString(weight: Weight?): String? {
        return if (weight == null) {
            null
        } else {
            json.encodeToString(weight)
        }
    }

    @TypeConverter
    fun stringToWeight(weightString: String?): Weight? {
        return if (weightString == null) {
            null
        } else {
            json.decodeFromString<Weight>(weightString)
        }
    }

    @TypeConverter
    fun bodyTempToString(bodyTemperature: BodyTemperature?): String? {
        return if (bodyTemperature == null) {
            null
        } else {
            json.encodeToString(bodyTemperature)
        }
    }

    @TypeConverter
    fun stringToBodyTemp(bodyTemperatureString: String?): BodyTemperature? {
        return if (bodyTemperatureString == null) {
            null
        } else {
            json.decodeFromString<BodyTemperature>(bodyTemperatureString)
        }
    }
}