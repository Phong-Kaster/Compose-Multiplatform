package org.astronex.olyn.data.database.menstruation.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_menstruation_day")
data class MenstruationDayEntity (
    @PrimaryKey val epochDay: Long,
)