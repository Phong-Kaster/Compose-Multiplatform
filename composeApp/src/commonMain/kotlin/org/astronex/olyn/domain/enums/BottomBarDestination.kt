package org.astronex.olyn.domain.enums


import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.calendar
import compose_multiplatform.composeapp.generated.resources.home
import compose_multiplatform.composeapp.generated.resources.ic_calendar
import compose_multiplatform.composeapp.generated.resources.ic_calendar_fill
import compose_multiplatform.composeapp.generated.resources.ic_home
import compose_multiplatform.composeapp.generated.resources.ic_home_fill
import compose_multiplatform.composeapp.generated.resources.ic_information
import compose_multiplatform.composeapp.generated.resources.ic_information_fill
import compose_multiplatform.composeapp.generated.resources.ic_insight
import compose_multiplatform.composeapp.generated.resources.ic_insight_fill
import compose_multiplatform.composeapp.generated.resources.ic_setting
import compose_multiplatform.composeapp.generated.resources.ic_setting_fill
import compose_multiplatform.composeapp.generated.resources.information
import compose_multiplatform.composeapp.generated.resources.insights
import compose_multiplatform.composeapp.generated.resources.settings
import org.astronex.olyn.navigation.ComposeDestination
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class BottomBarDestination(
    val text: StringResource,
    val icon: DrawableResource,
    val fillIcon: DrawableResource,
//    val destination: Int,
//    val directions: Int,
    val defaultDestination: ComposeDestination,
) {
    Home(
        text = Res.string.home,
        icon = Res.drawable.ic_home,
        fillIcon = Res.drawable.ic_home_fill,
//        destination = Res.id.homeFragment,
//        directions = Res.id.toHome,
        defaultDestination = ComposeDestination.Home,
    ),
    Calendar(
        text = Res.string.calendar,
        icon = Res.drawable.ic_calendar,
        fillIcon = Res.drawable.ic_calendar_fill,
//        destination = Res.id.calendarFragment,
//        directions = Res.id.toCalendar,
        defaultDestination = ComposeDestination.Calendar,
    ),
    Insight(
        text = Res.string.insights,
        icon = Res.drawable.ic_insight,
        fillIcon = Res.drawable.ic_insight_fill,
//        destination = Res.id.insightFragment,
//        directions = Res.id.toInsight,
        defaultDestination = ComposeDestination.Insight,
    ),
    Information(
        text = Res.string.information,
        icon = Res.drawable.ic_information,
        fillIcon = Res.drawable.ic_information_fill,
//        destination = Res.id.informationFragment,
//        directions = Res.id.toInformation,
        defaultDestination = ComposeDestination.Information,
    ),
    Setting(
        text = Res.string.settings,
        icon = Res.drawable.ic_setting,
        fillIcon = Res.drawable.ic_setting_fill,
//        destination = Res.id.settingFragment,
//        directions = Res.id.toSetting,
        defaultDestination = ComposeDestination.Setting,
    ),
}
