package org.astronex.olyn.ui.menstruation.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.apply
import compose_multiplatform.composeapp.generated.resources.ic_back
import compose_multiplatform.composeapp.generated.resources.language
import org.astronex.olyn.changeLanguage
import org.astronex.olyn.domain.enums.Language
import org.astronex.olyn.ui.component.ButtonBottomBar
import org.astronex.olyn.ui.component.CoreLayout
import org.astronex.olyn.ui.component.CoreTopBar
import org.astronex.olyn.ui.menstruation.setting.component.LanguageSelector
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingLanguageScreen(
    onBackClick: () -> Unit = {},
) {
    val settingViewModel = koinViewModel<SettingViewModel>()

    SettingLanguageLayout(
        uiState = settingViewModel.uiState.collectAsState().value,
        onChangeLanguage = {
            settingViewModel.changeLanguage(it)
            changeLanguage(languageCode = it.code)
        },
        onConfirmLanguage = {},
        onBackClick = onBackClick,
    )
}

@Composable
fun SettingLanguageLayout(
    uiState: SettingUiState = SettingUiState(),
    onChangeLanguage: (Language) -> Unit = {},
    onConfirmLanguage: () -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    CoreLayout(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        topBar = {
            CoreTopBar(
                title = stringResource(Res.string.language),
                titleTextStyle = customizedTextStyle(
                    fontSize = 16,
                    fontWeight = 500,
                    color = Color.Black
                ),
                leftIcon = Res.drawable.ic_back,
                onClickLeft = onBackClick,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                LanguageSelector(
                    languages = Language.entries,
                    selectedLanguage = uiState.language,
                    onSelectLanguage = {
                        onChangeLanguage(it)
                    },
                    modifier = Modifier
                )

                ButtonBottomBar(
                    titleText = Res.string.apply,
                    onClick = onBackClick,
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewSettingLanguageScreen() {
    SettingLanguageScreen()
}