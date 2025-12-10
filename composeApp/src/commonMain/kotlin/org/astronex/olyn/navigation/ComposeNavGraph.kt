package org.astronex.olyn.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.astronex.olyn.ui.menstruation.calendar.CalendarScreen
import org.astronex.olyn.ui.menstruation.home.HomeScreen
import org.astronex.olyn.ui.menstruation.information.InformationItemDetailScreen
import org.astronex.olyn.ui.menstruation.information.InformationItemListScreen
import org.astronex.olyn.ui.menstruation.information.InformationScreen
import org.astronex.olyn.ui.menstruation.insight.InsightScreen
import org.astronex.olyn.ui.menstruation.onboard.OnboardScreen
import org.astronex.olyn.ui.menstruation.setting.SettingLanguageScreen
import org.astronex.olyn.ui.menstruation.setting.SettingScreen
import org.astronex.olyn.ui.splash.SplashScreen


val LocalNavController = staticCompositionLocalOf<NavController> {
    error("NavController not provided")
}


@Composable
fun ComposeNavGraph() {

    // Creates the NavController
    val navController = rememberNavController()

    // rememberUpdatedState
//    var startDestinationValue: ComposeDestination by remember { mutableStateOf(ComposeDestination.Splash) }
//    val startDestination by rememberUpdatedState(newValue = startDestinationValue)

    CompositionLocalProvider(
        values = arrayOf(LocalNavController provides navController),
        content = {
            // Creates the NavHost with the navigation graph consisting of supplied destinations
            NavHost(
                navController = navController,
                startDestination = ComposeDestination.Splash,
                builder = {
                    composable<ComposeDestination.Splash> {// ----- Splash Screen
                        SplashScreen(
                            goHome = {
                                //startDestinationValue = ComposeDestination.Home
                                // Tells your Compose Navigation controller to navigate to a new screen (Home in this case).
                                navController.navigate(
                                    route = ComposeDestination.Home,// → The destination route name. You’re telling the app: “Go to the Home screen.”
                                    builder = {
                                        popUpTo( // Before navigating, clear the back stack up to the Splash route.
                                            route = ComposeDestination.Splash,
                                            popUpToBuilder = {
                                                // →   inclusive = true Means that the Splash destination itself is also removed from the back stack.
                                                // (If it were false, Splash would remain, and pressing Back from Home could go back to Splash.)
                                                inclusive = true
                                            }
                                        )
                                    })
                            },
                            goOnboard = {
                                navController.navigate(
                                    route = ComposeDestination.Onboard,
                                    builder = {
                                        popUpTo( // Before navigating, clear the back stack up to the Splash route.
                                            route = ComposeDestination.Splash,
                                            popUpToBuilder = {
                                                // →   inclusive = true Means that the Splash destination itself is also removed from the back stack.
                                                // (If it were false, Splash would remain, and pressing Back from Home could go back to Splash.)
                                                inclusive = true
                                            }
                                        )
                                    }
                                )
                            }
                        )
                    } // end Splash

                    composable<ComposeDestination.Onboard> {// ----- Onboard Screen
                        OnboardScreen(
                            onOpenHome = {
                                navController.navigate(
                                    route = ComposeDestination.Home,
                                    builder = {
                                        popUpTo( // Before navigating, clear the back stack up to the Splash route.
                                            route = ComposeDestination.Onboard,
                                            popUpToBuilder = {
                                                // →   inclusive = true Means that the Splash destination itself is also removed from the back stack.
                                                // (If it were false, Splash would remain, and pressing Back from Home could go back to Splash.)
                                                inclusive = true
                                            }
                                        )
                                    }
                                )
                            },
                        )
                    }// end Onboard

                    // ---- Home (fade animation)
                    composable<ComposeDestination.Home>(
                        enterTransition = { fadeIn(tween(300)) },
                        exitTransition = { fadeOut(tween(300)) }
                    ) {
                        HomeScreen()
                    }// end Home


                    // ---- Calendar (fade animation)
                    composable<ComposeDestination.Calendar>(
                        enterTransition = { fadeIn(tween(300)) },
                        exitTransition = { fadeOut(tween(300)) }
                    ) {
                        CalendarScreen()
                    }

                    // ---- Insight (fade animation)
                    composable<ComposeDestination.Insight>(
                        enterTransition = { fadeIn(tween(300)) },
                        exitTransition = { fadeOut(tween(300)) }
                    ) {
                        InsightScreen()
                    }// end Insight

                    // ---- Information (fade animation)
                    composable<ComposeDestination.Information>(
                        enterTransition = { fadeIn(tween(300)) },
                        exitTransition = { fadeOut(tween(300)) },
                    ) {
                        InformationScreen(
                            onOpenInfoDetails = { infoId ->
                                navController.navigate(
                                    route = ComposeDestination.InformationDetail(infoId)
                                )
                            },
                            onSeeAllList = { categoryId ->
                                navController.navigate(
                                    route = ComposeDestination.InformationSeeAll(
                                        categoryId
                                    )
                                )
                            }
                        )
                    }// end Information

                    // ---- Information Detail (slide animation)
                    composable<ComposeDestination.InformationDetail>(
                        enterTransition = {
                            slideInHorizontally(
                                initialOffsetX = { it },
                                animationSpec = tween(300)
                            )
                        },
                        exitTransition = {
                            slideOutHorizontally(
                                targetOffsetX = { -it },
                                animationSpec = tween(300)
                            )
                        },
                    ) { backStackEntry: NavBackStackEntry -> // ----- Information Detail Screen
                        val destination: ComposeDestination.InformationDetail =
                            backStackEntry.toRoute() // change backStack to route object
                        val infoId = destination.infoId // access the argument

                        InformationItemDetailScreen(
                            infoId = infoId,
                            onBackClick = { navController.popBackStack() }
                        )

                    }// end Information Detail

                    composable<ComposeDestination.InformationSeeAll> { backStackEntry: NavBackStackEntry -> // ----- Information See All Screen
                        val destination: ComposeDestination.InformationSeeAll =
                            backStackEntry.toRoute() // change backStack to route object
                        val categoryId = destination.categoryId // access the argument

                        InformationItemListScreen(
                            categoryId = categoryId,
                            onItemClick = { infoId ->
                                navController.navigate(
                                    route = ComposeDestination.InformationDetail(
                                        infoId
                                    )
                                )
                            },
                            onBackClick = { navController.popBackStack() }
                        )
                    } // end Information See All

                    composable<ComposeDestination.Setting>(
                        enterTransition = { fadeIn(tween(300)) },
                        exitTransition = { fadeOut(tween(300)) }
                    ) {// ----- Setting Screen
                        SettingScreen(
                            onSettingLanguage = { navController.navigate(route = ComposeDestination.SettingLanguage) }
                        )
                    }// end Setting

                    composable<ComposeDestination.SettingLanguage> {// ----- Setting Language Screen
                        SettingLanguageScreen(
                            onBackClick = { navController.popBackStack() }
                        )
                    }// end Setting Language
                })
        })
}
