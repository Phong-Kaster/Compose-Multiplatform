package org.astronex.olyn.domain.model

import androidx.compose.runtime.Immutable

/**
 * FertilePhase represents the fertile window information for a menstrual cycle.
 *
 * @property startDay The epoch day when the fertile window begins (inclusive).
 * For example, 1672531200000L represents January 1, 2023.
 * @property length The total number of days the fertile window lasts
 * @property ovulationDay The epoch day when ovulation is predicted to occur,
 * or -1 if ovulation day cannot be determined (irregular cycles)
 */
@Immutable
data class FertilePhase(
    val startDay: Long,
    val length: Int,
    val ovulationDay: Long
)
