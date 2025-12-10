package org.astronex.olyn.ui.theme


import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.inter_black
import compose_multiplatform.composeapp.generated.resources.inter_bold
import compose_multiplatform.composeapp.generated.resources.inter_extra_bold
import compose_multiplatform.composeapp.generated.resources.inter_extra_light
import compose_multiplatform.composeapp.generated.resources.inter_light
import compose_multiplatform.composeapp.generated.resources.inter_medium
import compose_multiplatform.composeapp.generated.resources.inter_regular
import compose_multiplatform.composeapp.generated.resources.inter_semi_bold
import compose_multiplatform.composeapp.generated.resources.inter_thin
import org.jetbrains.compose.resources.Font

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

@Composable
fun fontFamilyInter(): FontFamily {
    return FontFamily(
        Font(Res.font.inter_black, weight = FontWeight.Black),
        Font(Res.font.inter_regular, weight = FontWeight.Normal),
        Font(Res.font.inter_bold, weight = FontWeight.Bold),
        Font(Res.font.inter_extra_bold, weight = FontWeight.ExtraBold),
        Font(Res.font.inter_semi_bold, weight = FontWeight.SemiBold),
        Font(Res.font.inter_medium, weight = FontWeight.Medium),
        Font(Res.font.inter_light, weight = FontWeight.Light),
        Font(Res.font.inter_extra_light, weight = FontWeight.ExtraLight),
        Font(Res.font.inter_thin, weight = FontWeight.Thin),
    )
}

@Composable
fun customizedTextStyle(
    fontSize: Int = 14,
    fontWeight: Int = 400,
    lineHeight: Int = (fontSize * 1.5f).toInt(),
    color: Color = Color.White,
    textDecoration: TextDecoration? = null,
    fontFamily: FontFamily = fontFamilyInter(),
): TextStyle = TextStyle(
    fontSize = fontSize.sp,
    fontWeight = FontWeight(fontWeight),
    lineHeight = lineHeight.sp,
    color = color,
    textDecoration = textDecoration,
    fontFamily = fontFamily
)

private val TextUnit.nonScaledSp
    @Composable
    get() = (this.value / LocalDensity.current.fontScale).sp


// This code snippet  support Android-only, to work with Compose Multiplatform we must rewrite it
//@Composable
//fun StyledText(
//    @StringRes stringId: Int,
//    modifier: Modifier = Modifier,
//    baseStyle: TextStyle = com.apero.lunacycle.ui.theme.customizedTextStyle(
//        fontSize = 16,
//        fontWeight = 400,
//        color = TextDefault
//    ),
//) {
//    val context = LocalContext.current
//    val spanned = try {
//        context.getText(stringId) as Spanned
//    } catch (e: Exception) {
//        SpannedString(context.getString(stringId))
//    }
//    val annotatedString = spanned.toAnnotatedString()
//
//    Text(
//        modifier = modifier.fillMaxWidth(), text = annotatedString, style = baseStyle
//    )
//}
//
////Source: https://iamjosephmj.medium.com/how-to-display-styled-strings-in-jetpack-compose-decd6b705746
//fun Spanned.toAnnotatedString(): AnnotatedString = buildAnnotatedString {
//    // Step 1: Copy over the raw text
//    append(this@toAnnotatedString.toString())
//
//    // Step 2: Go through each span
//    getSpans(0, length, Any::class.java).forEach { span ->
//        val start = getSpanStart(span)
//        val end = getSpanEnd(span)
//        when (span) {
//            // Bold, Italic, Bold-Italic
//            is StyleSpan -> {
//                when (span.style) {
//                    Typeface.BOLD -> addStyle(
//                        SpanStyle(fontWeight = FontWeight.Bold),
//                        start,
//                        end
//                    )
//
//                    Typeface.ITALIC -> addStyle(
//                        SpanStyle(fontStyle = FontStyle.Italic),
//                        start,
//                        end
//                    )
//
//                    Typeface.BOLD_ITALIC -> addStyle(
//                        SpanStyle(
//                            fontWeight = FontWeight.Bold,
//                            fontStyle = FontStyle.Italic
//                        ),
//                        start,
//                        end
//                    )
//                }
//            }
//            // Underline
//            is UnderlineSpan -> {
//                addStyle(
//                    SpanStyle(textDecoration = TextDecoration.Underline),
//                    start,
//                    end
//                )
//            }
//            // Foreground Color
//            is ForegroundColorSpan -> {
//                addStyle(
//                    SpanStyle(color = Color(span.foregroundColor)),
//                    start,
//                    end
//                )
//            }
//            // Background Color
//            is BackgroundColorSpan -> {
//                addStyle(
//                    SpanStyle(background = Color(span.backgroundColor)),
//                    start,
//                    end
//                )
//            }
//            // Strikethrough (Line-through)
//            is StrikethroughSpan -> {
//                addStyle(
//                    SpanStyle(textDecoration = TextDecoration.LineThrough),
//                    start,
//                    end
//                )
//            }
//            // Relative Size (scales the text)
//            is RelativeSizeSpan -> {
//                // For a real-world app, you'd need the base font size to multiply by span.sizeChange.
//                // Here, for simplicity, let's assume a base size or do a rough conversion:
//                val baseFontSize = 16.sp
//                val newFontSize = baseFontSize * span.sizeChange
//                addStyle(
//                    SpanStyle(fontSize = newFontSize),
//                    start,
//                    end
//                )
//            }
//            // URL or clickable text
//            is URLSpan -> {
//                // You can store the URL as an annotation and optionally add a style
//                addStringAnnotation(
//                    tag = "URL",
//                    annotation = span.url,
//                    start = start,
//                    end = end
//                )
//                // Optional: add a style (color or underline) to make it look clickable
//                addStyle(
//                    SpanStyle(
//                        color = Color.Blue,
//                        textDecoration = TextDecoration.Underline
//                    ),
//                    start,
//                    end
//                )
//            }
//            // Subscript
//            is SubscriptSpan -> {
//                // Compose doesn't have a built-in subscript style,
//                // so you'd either skip or handle it with a custom solution
//                // For demonstration, let's apply a smaller font size
//                val baseFontSize = 16.sp
//                addStyle(
//                    SpanStyle(
//                        fontSize = baseFontSize * 0.8f,
//                        baselineShift = BaselineShift.Subscript
//                    ),
//                    start,
//                    end
//                )
//            }
//            // Superscript
//            is SuperscriptSpan -> {
//                // Similarly, let's demonstrate a smaller font size with a shift
//                val baseFontSize = 16.sp
//                addStyle(
//                    SpanStyle(
//                        fontSize = baseFontSize * 0.8f,
//                        baselineShift = BaselineShift.Superscript
//                    ),
//                    start,
//                    end
//                )
//            }
//            // You can keep adding more span types as needed
//            else -> {}
//        }
//    }
//}

@Composable
fun StyledText(
    htmlText: String,
    modifier: Modifier = Modifier,
    baseStyle: TextStyle = TextStyle.Default
) {
    val annotated = htmlToAnnotatedString(htmlText)
    Text(
        modifier = modifier,
        text = annotated,
        style = baseStyle
    )
}

/**
 * Convert a very simple subset of HTML (<b>, <i>, <u>) into an AnnotatedString
 * so it can be displayed in Compose Multiplatform's Text()
 *
 * 🔑 Key things for you:
 *
 * buildAnnotatedString {} → lets us build rich styled text.
 *
 * pushStyle(SpanStyle(...)) → starts applying a style (bold, italic, underline).
 *
 * append(content) → adds the styled text.
 *
 * pop() → removes the style, so next text won’t be affected.
 *
 * This is a minimal parser, only works for <b>, <i>, <u>. If you want more tags later (<br>, <p>, <font>…), we can extend it.
 */
fun htmlToAnnotatedString(html: String): AnnotatedString {
    return buildAnnotatedString {
        var i = 0
        // Process the input HTML string character by character
        while (i < html.length) {
            when {
                // Detect <b>...</b> for bold text
                html.startsWith("<b>", i) -> {
                    val end = html.indexOf("</b>", i)
                    if (end != -1) {
                        val content = html.substring(i + 3, end)
                        pushStyle(SpanStyle(fontWeight = FontWeight.Bold)) // apply bold
                        append(content)
                        pop() // remove bold style
                        i = end + 4
                    } else {
                        append("<b>")
                        i += 3
                    }
                }
                // Detect <i>...</i> for italic text
                html.startsWith("<i>", i) -> {
                    val end = html.indexOf("</i>", i)
                    if (end != -1) {
                        val content = html.substring(i + 3, end)
                        pushStyle(SpanStyle(fontStyle = FontStyle.Italic)) // apply italic
                        append(content)
                        pop()
                        i = end + 4
                    } else {
                        append("<i>")
                        i += 3
                    }
                }
                // Detect <u>...</u> for underlined text
                html.startsWith("<u>", i) -> {
                    val end = html.indexOf("</u>", i)
                    if (end != -1) {
                        val content = html.substring(i + 3, end)
                        pushStyle(SpanStyle(textDecoration = TextDecoration.Underline)) // apply underline
                        append(content)
                        pop()
                        i = end + 4
                    } else {
                        append("<u>")
                        i += 3
                    }
                }
                // Otherwise, treat it as normal text
                else -> {
                    append(html[i])
                    i++
                }
            }
        }
    }
}