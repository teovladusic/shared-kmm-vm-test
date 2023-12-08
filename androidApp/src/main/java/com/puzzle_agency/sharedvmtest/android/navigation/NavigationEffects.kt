package com.puzzle_agency.sharedvmtest.android.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.puzzle_agency.sharedvmtest.navigation.NavigationIntent
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
                    if (route != null) navHostController.popBackStack(route, intent.inclusive)
                    else navHostController.popBackStack()
                }

                is NavigationIntent.NavigateTo -> navHostController.navigateTo(
                    intent.destination.route,
                    intent.popUpToRoute,
                    intent.inclusive,
                    intent.isSingleTop
                )

                is NavigationIntent.PresentSheet -> navHostController.navigateTo(
                    intent.destination.route,
                    intent.popUpToRoute,
                    intent.inclusive,
                    intent.isSingleTop
                )
            }
        }
    }
}

private fun NavHostController.navigateTo(
    route: String,
    popUpToRoute: String? = null,
    inclusive: Boolean = false,
    isSingleTop: Boolean = false,
) {
    navigate(route) {
        launchSingleTop = isSingleTop

        popUpToRoute?.let { popUpToRoute ->
            popUpTo(popUpToRoute) { this.inclusive = inclusive }
        }
    }
}