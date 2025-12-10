package org.astronex.olyn.data.database.symptom.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.astronex.olyn.domain.model.BodyTemperature
import org.astronex.olyn.domain.model.Weight


@Entity(tableName = "table_symptom_diary")
data class SymptomDiaryEntity(
    @PrimaryKey val epochDay: Long,
    @ColumnInfo(name = "weight") val weight: Weight,
    @ColumnInfo(name = "body_temperature") val bodyTemperature: BodyTemperature,
    @ColumnInfo(name = "list_of_symptom")val listOfSymptom: String
)