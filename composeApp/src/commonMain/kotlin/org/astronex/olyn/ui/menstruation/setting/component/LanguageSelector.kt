package org.astronex.olyn.ui.menstruation.setting.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.ic_radio_selected_checked
import compose_multiplatform.composeapp.generated.resources.ic_radio_unselected_checked
import compose_multiplatform.composeapp.generated.resources.icon
import compose_multiplatform.composeapp.generated.resources.language
import org.astronex.olyn.domain.enums.Language
import org.astronex.olyn.ui.theme.Black90
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LanguageSelector(
    languages: List<Language>,
    selectedLanguage: Language?,
    onSelectLanguage: (Language) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(0.dp))
        }

        items(languages) { language ->
            LanguageItem(
                language = language,
                selected = language == selectedLanguage,
                onClick = { onSelectLanguage(language) },
            )
        }

        item {
            Spacer(modifier = Modifier.height(0.dp))
        }
    }
}

@Composable
fun LanguageItem(
    language: Language,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (selected) {
                    Modifier
                        .clip(shape = RoundedCornerShape(12.dp))
                        .background(Color(0xFFFFE8E4))
                } else Modifier
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(Color.White)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painterResource(language.drawableId),
            contentDescription = stringResource(Res.string.language),
            modifier = Modifier.size(28.dp),
            contentScale = ContentScale.Fit,
        )
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(language.displayName),
            style = customizedTextStyle(
                fontSize = 16,
                fontWeight = 500,
                color = Black90
            ),
        )
        Box {
            Image(
                painter = painterResource(if (selected) Res.drawable.ic_radio_selected_checked else Res.drawable.ic_radio_unselected_checked),
                contentDescription = stringResource(Res.string.icon),
                modifier = Modifier.size(24.dp),
            )
        }
    }
}


@Preview
@Composable
fun PreviewLanguageItem() {
    Column {
        LanguageItem(Language.English, true, Modifier, {})
    }
}

@Preview
@Composable
fun PreviewLanguageSelector() {
    LanguageSelector(
        modifier = Modifier.fillMaxSize(),
        languages = Language.entries,
        selectedLanguage = Language.English,
        onSelectLanguage = {}
    )
}
