package org.astronex.olyn.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlinx.datetime.*
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlin.text.StringBuilder

/**
 * Because java.time.LocalDate is not available in all platforms, we use kotlinx.datetime.LocalDate
 * and provide a utility function to get the current date.
 */
object LocalDateUtil {

    // this formatter is used to format LocalDate to "dd/MM/yyyy". Return 04/09/2025
    val formatterDDMMYYY = LocalDate.Format(
        block = {
            day(padding = Padding.ZERO)
            char('/')
            monthNumber()
            char('/')
            year()
        }
    )

    val formatterDDMMM = LocalDate.Format(
        block = {
            monthNumber()
            char(':')
            day(padding = Padding.ZERO)
        }
    )

    /*********************
     * now() equals to LocalDate.now() in JVM-only code
     */
    @OptIn(ExperimentalTime::class)
    fun now(): LocalDate {
        val nowInstant = Clock.System.now()
        val localDateTime = nowInstant.toLocalDateTime(TimeZone.currentSystemDefault())
        return localDateTime.date
    }

    /*********************
     * ofEpochDay equals LocalDate.ofEpochDay(epochDay) in JVM-only code
     */
    @OptIn(ExperimentalTime::class)
    fun ofEpochDay(epochDay: Long): LocalDate {
        val instant = Instant.fromEpochMilliseconds(epochDay * 24 * 60 * 60 * 1000) // epochDay → ms
        return instant.toLocalDateTime(TimeZone.UTC).date
    }

    /*********************
     * because DateTimeFormatter is not available in Compose Multiplatform so
     * that this function will take this responsibility
     */
//    fun LocalDate.format(pattern: String): String {
//        // Precompute parts
//        val day = this.dayOfMonth
//        val month = this.month
//        val year = this.year
//        val dow = this.dayOfWeek
//
//        // English month/weekday names (you could extend for i18n later)
//        val monthFull = month.name.lowercase().replaceFirstChar { it.titlecase() } // January
//        val monthShort = monthFull.take(3) // Jan
//        val dowFull = dow.name.lowercase().replaceFirstChar { it.titlecase() } // Monday
//        val dowShort = dowFull.take(3) // Mon
//
//        // Replace longest tokens first to avoid conflicts
//        return pattern
//            .replace("yyyy", year.toString())
//            .replace("yy", (year % 100).toString().padStart(2, '0'))
//            .replace("MMMM", monthFull)
//            .replace("MMM", monthShort)
//            .replace("MM", month.number.toString().padStart(2, '0'))
//            .replace("M", month.number.toString())
//            .replace("dd", day.toString().padStart(2, '0'))
//            .replace("d", day.toString())
//            .replace("EEEE", dowFull)
//            .replace("EEE", dowShort)
//    }

    // Days between 1970-01-01 and this date
    fun LocalDate.toEpochDay(): Long {
        val epoch = LocalDate(1970, 1, 1)
        return this.daysUntil(epoch).toLong() * -1
    }
}