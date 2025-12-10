package org.astronex.olyn.domain.enums


import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.english
import compose_multiplatform.composeapp.generated.resources.french
import compose_multiplatform.composeapp.generated.resources.hindi
import compose_multiplatform.composeapp.generated.resources.ic_language_english
import compose_multiplatform.composeapp.generated.resources.ic_language_espano
import compose_multiplatform.composeapp.generated.resources.ic_language_french
import compose_multiplatform.composeapp.generated.resources.ic_language_hindi
import compose_multiplatform.composeapp.generated.resources.ic_language_japanese
import compose_multiplatform.composeapp.generated.resources.ic_language_korean
import compose_multiplatform.composeapp.generated.resources.ic_language_portuguese
import compose_multiplatform.composeapp.generated.resources.ic_language_turkish
import compose_multiplatform.composeapp.generated.resources.japanese
import compose_multiplatform.composeapp.generated.resources.korean
import compose_multiplatform.composeapp.generated.resources.portuguese
import compose_multiplatform.composeapp.generated.resources.spanish
import compose_multiplatform.composeapp.generated.resources.turkish
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class Language(
    val code: String,
    val displayName: StringResource,
    val drawableId: DrawableResource,
) {
    English(code = "en", displayName = Res.string.english, drawableId = Res.drawable.ic_language_english),
    French(code = "fr", displayName = Res.string.french, drawableId = Res.drawable.ic_language_french),
    Japanese(code = "ja", displayName = Res.string.japanese, drawableId = Res.drawable.ic_language_japanese),
    Korean(code = "ko", displayName = Res.string.korean, drawableId = Res.drawable.ic_language_korean),
    Hindi(code = "hi", displayName = Res.string.hindi, drawableId = Res.drawable.ic_language_hindi),
    Portuguese(code = "pt", displayName = Res.string.portuguese, drawableId = Res.drawable.ic_language_portuguese),
    Turkish(code = "tr", displayName = Res.string.turkish, drawableId = Res.drawable.ic_language_turkish),
    Spanish(code = "es", displayName = Res.string.spanish, drawableId = Res.drawable.ic_language_espano),

    //Malaysia(code = "ms", displayName = "Malaysian", drawableId = Res.drawable.ic_language_malaysia),
    //German(code = "de", displayName = "German", drawableId = Res.drawable.ic_language_germany),

//    SimplifiedChinese(code = "zh", displayName = "Simplified Chinese", drawableId = Res.drawable.ic_language_chinese),
//    Russian(code = "ru", displayName = "Russian", drawableId = Res.drawable.ic_language_russian),
//    Bengali(code = "bn", displayName = "Bengali", drawableId = Res.drawable.ic_language_bengali),
//    Marathi(code = "mr", displayName = "Marathi", drawableId = Res.drawable.ic_language_marathi),
//    Telugu(code = "te", displayName = "Telugu", drawableId = Res.drawable.ic_language_telugu),
//    Tamil(code = "ta", displayName = "Tamil", drawableId = Res.drawable.ic_language_tamil),
//    Vietnamese(code = "vi", displayName = "Vietnamese", drawableId = Res.drawable.ic_language_vietnamese),
//    Italian(code = "it", displayName = "Italian", drawableId = Res.drawable.ic_language_italian),
//    Thailand(code = "th", displayName = "Thai", drawableId = Res.drawable.ic_language_thai)


    ;

    companion object {
        fun getDefault(): Language = English

        fun getByCode(code: String?): Language {
            if (code == null) return getDefault()
            return entries.firstOrNull { it.code == code } ?: English
        }

        fun List<Language>.getSortedList(): List<Language> {
            val defaultLocale = LocaleList.current.firstOrNull() ?: Locale.current
            val defaultCode = defaultLocale.language

            val list = this.toMutableList()
            val indexOfDefault = list.indexOfFirst { it.code == defaultCode }
            if (indexOfDefault > 0) {
                val item = list.removeAt(indexOfDefault)
                list.add(0, item)
            }
            return list
        }
    }
}

