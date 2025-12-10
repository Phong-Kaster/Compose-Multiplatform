package org.astronex.olyn.domain.enums

import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.fri
import compose_multiplatform.composeapp.generated.resources.friday
import compose_multiplatform.composeapp.generated.resources.mon
import compose_multiplatform.composeapp.generated.resources.monday
import compose_multiplatform.composeapp.generated.resources.sat
import compose_multiplatform.composeapp.generated.resources.saturday
import compose_multiplatform.composeapp.generated.resources.sun
import compose_multiplatform.composeapp.generated.resources.sunday
import compose_multiplatform.composeapp.generated.resources.thu
import compose_multiplatform.composeapp.generated.resources.thursday
import compose_multiplatform.composeapp.generated.resources.tue
import compose_multiplatform.composeapp.generated.resources.tuesday
import compose_multiplatform.composeapp.generated.resources.wed
import compose_multiplatform.composeapp.generated.resources.wednesday
import org.jetbrains.compose.resources.StringResource

/**
 * Represents a day of the week.
 *
 * @property fullName The string resource ID for the full name of the day.
 * @property shortName The string resource ID for the short name (abbreviation) of the day.
 * @property dayOfWeek The numerical representation of the day of the week, with 1 being Sunday and 7 being Saturday. Follow by Calendar.DAY_OF_WEEK
 */
enum class Weekday(
    val fullName: StringResource,
    val shortName: StringResource,
    val dayOfWeek: Int,
) {
    MONDAY(fullName = Res.string.monday, shortName = Res.string.mon, dayOfWeek = 2),
    TUESDAY(fullName = Res.string.tuesday, shortName = Res.string.tue, dayOfWeek = 3),
    WEDNESDAY(fullName = Res.string.wednesday, shortName = Res.string.wed, dayOfWeek = 4),
    THURSDAY(fullName = Res.string.thursday, shortName = Res.string.thu, dayOfWeek = 5),
    FRIDAY(fullName = Res.string.friday, shortName = Res.string.fri, dayOfWeek = 6),
    SATURDAY(fullName = Res.string.saturday, shortName = Res.string.sat, dayOfWeek = 7),
    SUNDAY(fullName = Res.string.sunday, shortName = Res.string.sun, dayOfWeek = 1),
}