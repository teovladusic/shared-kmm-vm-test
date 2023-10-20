package com.puzzle_agency.sharedvmtest.android.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.puzzle_agency.navigation.NavigationIntent
import com.puzzle_agency.navigation.SwitchRootDestination
import com.puzzle_agency.navigation.destination.Destination
import com.puzzle_agency.sharedvmtest.android.NavGraphs
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController,
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    val route = intent.route
                    if (route != null) {
                        navHostController.popBackStack(route, intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }

                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.destination.fullRoute) {
                        launchSingleTop = intent.isSingleTop
//                        restoreState = BottomBarDestination.subClasses.any {
//                            it.route == intent.route
//                        }
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }
                }

                is NavigationIntent.SwitchRootScreen -> {
                    when (intent.destination) {
                        SwitchRootDestination.Home ->
                            navHostController.navigate(Destination.HomeScreen.fullRoute) {
                                popUpTo(NavGraphs.root.route) { inclusive = true }
                            }

                        SwitchRootDestination.Auth -> navHostController.navigate(Destination.Auth1.fullRoute) {
                            popUpTo(NavGraphs.root.route) { inclusive = true }
                        }
                    }
                }
            }
        }
    }
}
