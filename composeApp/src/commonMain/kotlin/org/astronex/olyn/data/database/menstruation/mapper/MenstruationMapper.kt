package org.astronex.olyn.data.database.menstruation.mapper

import org.astronex.olyn.data.database.menstruation.entity.MenstruationDayEntity
import org.astronex.olyn.domain.model.MenstruationDay


object MenstruationMapper {
    fun MenstruationDayEntity.toModel(): MenstruationDay {
        return MenstruationDay(
            epochDay = epochDay
        )
    }

    fun MenstruationDay.toEntity(): MenstruationDayEntity {
        return MenstruationDayEntity(
            epochDay = epochDay
        )
    }
}