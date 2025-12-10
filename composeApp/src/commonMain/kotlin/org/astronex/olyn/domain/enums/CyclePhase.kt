package org.astronex.olyn.domain.enums

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.follicular
import compose_multiplatform.composeapp.generated.resources.img_cycle_phase_fertile
import compose_multiplatform.composeapp.generated.resources.img_cycle_phase_fertile_box
import compose_multiplatform.composeapp.generated.resources.img_cycle_phase_follicular
import compose_multiplatform.composeapp.generated.resources.img_cycle_phase_follicular_box
import compose_multiplatform.composeapp.generated.resources.img_cycle_phase_luteal
import compose_multiplatform.composeapp.generated.resources.img_cycle_phase_luteal_box
import compose_multiplatform.composeapp.generated.resources.img_cycle_phase_menstruation
import compose_multiplatform.composeapp.generated.resources.img_cycle_phase_menstruation_box
import compose_multiplatform.composeapp.generated.resources.img_cycle_phase_none
import compose_multiplatform.composeapp.generated.resources.img_cycle_phase_none_box
import compose_multiplatform.composeapp.generated.resources.luteal
import compose_multiplatform.composeapp.generated.resources.menstruation
import compose_multiplatform.composeapp.generated.resources.none
import compose_multiplatform.composeapp.generated.resources.ovulation
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

/**
 * Represents the four distinct phases of a menstrual cycle with their associated UI styling properties.
 *
 * This enum defines the visual and textual representation for each phase of the menstrual cycle:
 * - **Menstruation** (Giai đoạn hành kinh): The bleeding phase at the start of the cycle
 * - **Follicular** (Giai đoạn trứng phát triển): The phase after menstruation where eggs develop
 * - **Fertile** (Thời kì rụng trứng/Thời kỳ dễ mang thai): The ovulation window when pregnancy is most likely
 * - **Luteal** (Giai đoạn hoàng thể/Thời kì phục hồi): The recovery phase after ovulation
 *
 * Each phase includes comprehensive styling properties for consistent UI theming:
 * - Background colors for phase-specific backgrounds
 * - Button colors and text colors for interactive elements
 * - Theme colors for general phase identification
 * - Optional painter resources for visual icons
 *
 * @param title String resource ID for the phase name localization
 * @param backgroundColor Background color used in phase-specific UI sections
 * @param buttonColor Primary button background color for the phase
 * @param textButtonColor Text color used on buttons for optimal contrast
 * @param themeColor Primary theme color representing the phase
 * @param painter Drawable resource ID for phase-specific icons (optional)
 *
 * Usage:
 * ```kotlin
 * val currentPhase = CyclePhase.Fertile
 * val phaseColor = currentPhase.themeColor
 * val phaseName = getString(currentPhase.title)
 * ```
 */
enum class CyclePhase(
    val title: StringResource = Res.string.menstruation,
    val backgroundColor: Color = Color(0xFFFFF0EC),
    val buttonColor: Color = Color(0xFFFF9988),
    val textButtonColor: Color = Color(0xFFFFFFFF),
    val themeColor: Color = Color(0xFFFF9988),
    val painter: DrawableResource = Res.drawable.img_cycle_phase_menstruation,
    val painterBox: DrawableResource = Res.drawable.img_cycle_phase_menstruation_box,
    val article: CyclePhaseItem?,

    val symptomSuggestionStartColor: Color = Color(0xFFFFEBE5),
    val symptomSuggestionEndColor: Color = Color(0xFFFFFCFA),
) {
    None(
        title = Res.string.none,
        backgroundColor = Color(0xFFFFFFFF),
        buttonColor = Color(0xFFFF9988),
        textButtonColor = Color(0xFFFFFFFF),

        themeColor = Color(0xFFFF9988),
        painter = Res.drawable.img_cycle_phase_none_box,
        painterBox = Res.drawable.img_cycle_phase_none,

        symptomSuggestionStartColor = Color(0xFFFFE5DF),
        symptomSuggestionEndColor = Color(0xFFFFFCFA),
        article = null
    ),

    /**
     * Menstruation phase - The bleeding period at the beginning of the cycle.
     * Uses warm red/pink color scheme to represent the active menstrual period.
     */
    Menstruation(
        title = Res.string.menstruation,
        backgroundColor = Color(0xFFFFF0EC),
        buttonColor = Color(0xFFFF9988),
        textButtonColor = Color(0xFFFFFFFF),
        themeColor = Color(0xFFFF9988),
        painter = Res.drawable.img_cycle_phase_menstruation,
        painterBox = Res.drawable.img_cycle_phase_menstruation_box,

        symptomSuggestionStartColor = Color(0xFFFFEAE5),
        symptomSuggestionEndColor = Color(0xFFFFFCFA),
        article = CyclePhaseItem.Menstruation
    ),

    /**
     * Follicular phase - The period after menstruation when follicles develop.
     * Uses yellow color scheme to represent growth and development.
     */
    Follicular(
        title = Res.string.follicular,
        backgroundColor = Color(0xFFFFFAE5),
        buttonColor = Color(0xFFFF9988),
        textButtonColor = Color(0xFFFFFFFF),
        themeColor = Color(0xFFFFE7A1),
        painter = Res.drawable.img_cycle_phase_follicular,
        painterBox = Res.drawable.img_cycle_phase_follicular_box,

        symptomSuggestionStartColor = Color(0xFFFFE7A1),
        symptomSuggestionEndColor = Color(0xFFFFFCFA),
        article = CyclePhaseItem.Follicular
    ),

    /**
     * Fertile phase - The ovulation window when pregnancy is most likely.
     * Uses blue color scheme with white buttons for clear visibility during this important phase.
     */
    Fertile(
        title = Res.string.ovulation,
        backgroundColor = Color(0xFFE4F6FF),
        buttonColor = Color(0xFFFFFFFF),
        textButtonColor = Color(0xFF000000),
        themeColor = Color(0xFF88C0FF),
        painter = Res.drawable.img_cycle_phase_fertile,
        painterBox = Res.drawable.img_cycle_phase_fertile_box,

        symptomSuggestionStartColor = Color(0xFF88C0FF),
        symptomSuggestionEndColor = Color(0xFFFFFCFA),
        article = CyclePhaseItem.Fertile
    ),

    /**
     * Luteal phase - The recovery period after ovulation before the next cycle.
     * Uses purple color scheme representing the final phase before cycle completion.
     */
    Luteal(
        title = Res.string.luteal,
        backgroundColor = Color(0xFFF6E7FF),
        buttonColor = Color(0xFFFFFFFF),
        textButtonColor = Color(0xFF000000),
        themeColor = Color(0xFFA08BFF),
        painter = Res.drawable.img_cycle_phase_luteal,
        painterBox = Res.drawable.img_cycle_phase_luteal_box,

        symptomSuggestionStartColor = Color(0xFFA08BFF),
        symptomSuggestionEndColor = Color(0xFFFFFCFA),
        article = CyclePhaseItem.Luteal
    )
}

@Composable
fun Prvasdf(modifier: Modifier = Modifier) {
    Text(text = "")
}