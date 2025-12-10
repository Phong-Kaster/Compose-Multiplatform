package org.astronex.olyn.domain.enums

enum class WeightUnit(
    val signal: String = "Kg",
){
    Kilogram(signal = "Kg"),
    Pound(signal = "Lb"),

    ;
    companion object {
        val multiplier = 2.20462f
        fun kgToPound(weightValue: Float): Float {
            return weightValue * multiplier
        }

        fun poundToKg(weightValue: Float): Float {
            return weightValue / multiplier
        }
    }
}