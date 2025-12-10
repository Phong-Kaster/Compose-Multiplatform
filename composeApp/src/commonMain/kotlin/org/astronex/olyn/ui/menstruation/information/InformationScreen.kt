package org.astronex.olyn.ui.menstruation.information

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.astronex.olyn.ui.core.CoreBottomBar
import org.astronex.olyn.ui.core.CoreLayout
import org.astronex.olyn.ui.shared.ComposeLifecycle
import org.astronex.olyn.ui.theme.SplashBrushBackground
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

// todo: sua cac ten class co chu fragment -> screen
// todo: kiem tra InformationItemDetailScreen xem con cach viet nao tot hon khong
// todo: chuyen cac layout vao 1 composable rieng biet -> de tao xem preview voi quy uoc screen+layout. VD: informationLayout
// todo: su dung expect/actual function de doi ngon ngu cho thiet bi
// todo: dem ComposeLifecycle toi tat ca cac screen con lai
@Composable
fun InformationScreen(
    onSeeAllList: (String) -> Unit = {},
    onOpenInfoDetails: (String) -> Unit = {},
) {
    val informationViewModel = koinViewModel<InfoViewModel>()

    ComposeLifecycle(
        screenName = "InformationScreen",
        onCreate = {},
        onStart = {},
        onResume = {},
        onPause = {},
        onStop = {},
        onDestroy = {},
    )

    // information layout
    // todo: chuyen core layout -> information layout
    CoreLayout(
        modifier = Modifier.background(brush = SplashBrushBackground),
        bottomBar = { CoreBottomBar() },
        content = {
            InformationLayout(
                uiState = informationViewModel.uiState.collectAsState().value,
                onOpenArticle = onOpenInfoDetails,
                onSeeFullClick = onSeeAllList
            )
        }
    )
}

@Preview
@Composable
private fun PreviewInformationScreen() {
    //
    InformationScreen()
}