package org.astronex.olyn.ui.component


import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.astronex.olyn.domain.enums.BottomBarDestination
import org.astronex.olyn.navigation.ComposeDestination
import org.astronex.olyn.navigation.LocalNavController
import org.astronex.olyn.navigation.isRoute
import org.astronex.olyn.ui.theme.PrimaryColor
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun CoreBottomBar() {
//    val activity: Activity? = LocalActivity.current

    // For navigating to other destinations
    val navController = LocalNavController.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

//    var showBottomSheet by remember { mutableStateOf(false) }

    // For storing size of button that has tutorials
    //val tutorial = LocalTutorial.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
//            .outerShadow(
//                shadowRadius = 6.dp,
//                spreadRadius = 5.dp,
//                shadowColor = Color(0x1A363636),
//                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
//            )
//            .height(70.dp)
            .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(color = Color.White)
            .padding(horizontal = 12.dp)// padding
            .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Bottom))
            .height(56.dp) // base bar height (material standard),
    ) {
        BottomBarDestination.entries.forEach { screen ->

            val selected = currentDestination.isRoute(screen.defaultDestination)


//            if (screen == BottomBarDestination.Home) {
            // home destination | when return home from any screen, show inter_back
            BottomBarElement(
                enable = selected,
                modifier = Modifier.weight(1f),
                destination = screen,
                onClick = {
                    navController.navigate(screen.defaultDestination) {
                        // standard bottom bar behavior
                        popUpTo(ComposeDestination.Home) {
                            saveState = true
                        } // pick your graph root
                        launchSingleTop = true
                        restoreState = true
                    }
                    // no activity means we are not in a fragment
//                        if (activity == null) {
//                            navController.navigate(screen.directions)
//                        }

//                        if (activity != null && AdsProvider.interBack.isAdReady()) {
//                            AdsProvider.interBack.showAds(
//                                activity = activity,
//                                onNextAction = {
//                                    navController.navigate(screen.directions)
//                                },
//                            )
//                        } else {
//                            navController.navigate(screen.directions)
//                        }

//                        if (currentDestination?.id != screen.defaultDestination) {
//                            navController.navigate(screen.directions)
//                        }
                }
            )
//            } else {
//                BottomBarElement(
//                    enable = currentDestination?.hierarchy?.any { it.id == screen.destination } == true,
//                    modifier = Modifier.weight(1f),
//                    destination = screen,
//                    onClick = {
//                        if (currentDestination?.id != screen.defaultDestination) {
//                            navController.navigate(screen.directions)
//                        }
//                    }
//                )
//            }
        }
    }
}

@Composable
private fun BottomBarElement(
    enable: Boolean,
    modifier: Modifier = Modifier,
    destination: BottomBarDestination,
    onClick: () -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false, radius = 35.dp),
                onClick = onClick
            ),
    ) {
        Icon(
            painter =
                if (enable)
                    painterResource(destination.fillIcon)
                else
                    painterResource(destination.icon),
            contentDescription = stringResource(destination.text),
            modifier = Modifier.size(24.dp),
            tint = if (enable) PrimaryColor else Color(0xFF9F9F9F),
        )

        Text(
            text = stringResource(destination.text),
            style = customizedTextStyle(
                fontSize = 12,
                fontWeight = 500,
            ),
            color = if (enable) PrimaryColor else Color(0xFF9F9F9F),
            maxLines = 1,
            modifier = Modifier
                .basicMarquee(Int.MAX_VALUE)
        )
    }
}

@Preview
@Composable
fun PreviewBottomBar() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        Column(modifier = Modifier.background(color = Color.White)) {
            CoreBottomBar(
            )
        }
    }
}