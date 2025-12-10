package org.astronex.olyn.domain.model

import kotlinx.serialization.Serializable
import org.astronex.olyn.domain.enums.TemperatureUnit

/**
 * The `@Serializable` annotation marks the `BodyTemperature` data class as serializable using Kotlin Serialization.
 * This means instances of this class can be easily converted to &
 * from formats like JSON, making it simple to save, load, or transmit the data.
 */
@Serializable
data class BodyTemperature (
    val value: Float = 0f,
    val unit: TemperatureUnit = TemperatureUnit.Celsius
) {
    companion object {
        fun generateFakeBodyTemperature(): BodyTemperature {
            return BodyTemperature(
                value = 36.5f,
                unit = TemperatureUnit.Celsius
            )
        }
    }
}