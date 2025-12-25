package org.astronex.olyn.ui.menstruation.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import kotlinx.datetime.format
import org.astronex.olyn.domain.enums.CyclePhase
import org.astronex.olyn.navigation.LocalNavController
import org.astronex.olyn.ui.component.CoreBottomBar
import org.astronex.olyn.ui.component.CoreLayout
import org.astronex.olyn.ui.menstruation.home.component.HomeTopBar
import org.astronex.olyn.ui.modifier.dynamicCycleBackground
import org.astronex.olyn.ui.shared.ComposeLifecycle
import org.astronex.olyn.util.LocalDateUtil
import org.astronex.olyn.view.model.MenstruationDayProperty
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen() {

    // view model
    val viewModel = koinViewModel<HomeViewModel>()


    ComposeLifecycle(
        screenName = "Home Screen",
        onCreate = {},
        onStart = {},
        onResume = {
//            viewModel.insertFakeSymptomData()
        },
        onPause = {},
        onStop = {},
        onDestroy = {},
    )

    // content
    HomeLayout(
        uiState = viewModel.uiState.collectAsState().value,
        onOpenPregnancy = {
        },
        onOpenNotification = {
        }
    )

    // bottom sheet

    // dialog
}

@Composable
private fun HomeLayout(
    uiState: HomeUiState = HomeUiState(),
    onOpenPregnancy: () -> Unit = {},
    onOpenNotification: () -> Unit = {},
) {
    val menstruationDayProperty by remember(uiState.menstruationDayProperty) {
        mutableStateOf(
            uiState.menstruationDayProperty
                ?: MenstruationDayProperty(cyclePhase = CyclePhase.Menstruation)
        )
    }

    var cyclePhaseValue by remember(uiState.menstruationDayProperty) {
        mutableStateOf(
            menstruationDayProperty.cyclePhase
        )
    }

    CoreLayout(
        modifier = Modifier.dynamicCycleBackground(cyclePhase = cyclePhaseValue),
        bottomBar = { CoreBottomBar() },
        topBar = {
            HomeTopBar(
                title = uiState.menstruationDay.localDate.format(LocalDateUtil.formatterDDMMM),
                onOpenPregnancy = onOpenPregnancy,
                onOpenNotification = onOpenNotification,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        content = {
            LazyColumn {
                item { }
            }
        }
    )
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        HomeLayout(
            uiState = HomeUiState()
        )
    }
}