package org.astronex.olyn.ui.menstruation.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.disclaimer
import compose_multiplatform.composeapp.generated.resources.ic_disclaimer_setting
import compose_multiplatform.composeapp.generated.resources.ic_language
import compose_multiplatform.composeapp.generated.resources.ic_privacy
import compose_multiplatform.composeapp.generated.resources.ic_reminder
import compose_multiplatform.composeapp.generated.resources.ic_term_of_service
import compose_multiplatform.composeapp.generated.resources.languages
import compose_multiplatform.composeapp.generated.resources.privacy
import compose_multiplatform.composeapp.generated.resources.reminder
import compose_multiplatform.composeapp.generated.resources.settings
import compose_multiplatform.composeapp.generated.resources.term_of_service
import org.astronex.olyn.getPlatformName
import org.astronex.olyn.openWebsite
import org.astronex.olyn.ui.core.CoreBottomBar
import org.astronex.olyn.ui.core.CoreLayout
import org.astronex.olyn.ui.core.CoreTopBar
import org.astronex.olyn.ui.menstruation.setting.component.SettingItem
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun SettingScreen(
    onSettingLanguage: () -> Unit = {},
) {
    SettingLayout(
        openLanguage = onSettingLanguage,
        openReminder = { /*TODO*/ },
        openTermOfService = { openWebsite(url = "https://astronex.ai/privacy/") },
        openPrivacy = { openWebsite(url = "https://astronex.ai/privacy/") },
        openDisclaimer = { /*TODO*/ },
    )
}

@Composable
private fun SettingLayout(
    openLanguage: () -> Unit = {},
    openReminder: () -> Unit = {},
    openTermOfService: () -> Unit = {},
    openPrivacy: () -> Unit = {},
    openDisclaimer: () -> Unit = {},
) {
    CoreLayout(
        topBar = {
            CoreTopBar(
                title = stringResource(Res.string.settings),
                titleTextStyle = customizedTextStyle(
                    fontSize = 16,
                    fontWeight = 500,
                    color = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        modifier = Modifier
            .background(Color.White)
            .statusBarsPadding(),
        bottomBar = {
            CoreBottomBar()
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 0.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                } //Space header for first item of the list whose shadow not be cut head

                item {
                    if (getPlatformName() == "Android") {
                        SettingItem(
                            modifier = Modifier,
                            nameId = Res.string.languages,
                            iconId = Res.drawable.ic_language,
                            onClick = openLanguage,
                        )
                    }
                }

                item {
                    SettingItem(
                        modifier = Modifier,
                        nameId = Res.string.reminder,
                        iconId = Res.drawable.ic_reminder,
                        onClick = openReminder,
                    )
                }

                item {
                    SettingItem(
                        modifier = Modifier.fillMaxWidth(),
                        nameId = Res.string.term_of_service,
                        iconId = Res.drawable.ic_term_of_service,
                        onClick = openTermOfService,
                    )
                }

                item {
                    SettingItem(
                        modifier = Modifier.fillMaxWidth(),
                        nameId = Res.string.privacy,
                        iconId = Res.drawable.ic_privacy,
                        onClick = openPrivacy,
                    )
                }

                item {
                    SettingItem(
                        modifier = Modifier.fillMaxWidth(),
                        nameId = Res.string.disclaimer,
                        iconId = Res.drawable.ic_disclaimer_setting,
                        onClick = openDisclaimer,
                    )
                }

                item { Spacer(modifier = Modifier.height(8.dp)) }
            }

        }
    )
}

@Preview
@Composable
private fun PreviewSettingScreen() {
    SettingLayout(
        openLanguage = {},
        openReminder = {},
        openTermOfService = {},
        openPrivacy = {},
        openDisclaimer = {}
    )
}