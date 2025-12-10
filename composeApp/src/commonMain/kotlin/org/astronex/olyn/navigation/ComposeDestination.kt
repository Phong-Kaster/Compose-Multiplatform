package org.astronex.olyn.navigation

// commonMain/kotlin/.../navigation/AppDestination.kt
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import kotlinx.serialization.Serializable

@Serializable
sealed interface ComposeDestination {
    @Serializable data object Splash : ComposeDestination
    @Serializable data object Onboard : ComposeDestination
    @Serializable data object Home : ComposeDestination
    @Serializable data object Calendar : ComposeDestination
    @Serializable data object Insight : ComposeDestination
    @Serializable data object Information : ComposeDestination
    @Serializable data object Setting : ComposeDestination
    @Serializable data object SettingLanguage : ComposeDestination

    @Serializable data class InformationDetail (val infoId: String) : ComposeDestination
    @Serializable data class InformationSeeAll (val categoryId: String) : ComposeDestination

//    @Serializable data class Calendar(val userId: String) // <-- with argument
}


fun NavDestination?.isRoute(dest: ComposeDestination): Boolean = when (dest) {
    is ComposeDestination.Home -> this?.hasRoute<ComposeDestination.Home>() == true
    is ComposeDestination.Calendar -> this?.hasRoute<ComposeDestination.Calendar>() == true
    is ComposeDestination.Insight -> this?.hasRoute<ComposeDestination.Insight>() == true
    is ComposeDestination.Information -> this?.hasRoute<ComposeDestination.Information>() == true
    is ComposeDestination.Setting -> this?.hasRoute<ComposeDestination.Setting>() == true
    is ComposeDestination.Splash -> this?.hasRoute<ComposeDestination.Splash>() == true
    is ComposeDestination.Onboard -> this?.hasRoute<ComposeDestination.Onboard>() == true
    is ComposeDestination.SettingLanguage -> this?.hasRoute<ComposeDestination.SettingLanguage>() == true
    is ComposeDestination.InformationDetail -> this?.hasRoute<ComposeDestination.InformationDetail>() == true
    is ComposeDestination.InformationSeeAll -> this?.hasRoute<ComposeDestination.InformationSeeAll>() == true
}