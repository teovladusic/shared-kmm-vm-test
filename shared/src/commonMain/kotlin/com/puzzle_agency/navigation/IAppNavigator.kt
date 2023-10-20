package com.puzzle_agency.navigation

import com.puzzle_agency.navigation.destination.DestinationScreen
import com.puzzle_agency.sharedvmtest.CommonFlow
import kotlinx.coroutines.channels.Channel

interface IAppNavigator {
    val navigationChannel: Channel<NavigationIntent>
    val navigationFlow: CommonFlow<NavigationIntent>

    suspend fun navigateBack(
        route: String? = null,
        inclusive: Boolean = false,
    )

    fun tryNavigateBack(
        route: String? = null,
        inclusive: Boolean = false,
    )

    suspend fun navigateTo(
        destination: DestinationScreen,
        popUpToRoute: String? = null,
        inclusive: Boolean = false,
        isSingleTop: Boolean = false,
    )

    fun tryNavigateTo(
        destination: DestinationScreen,
        popUpToRoute: String? = null,
        inclusive: Boolean = false,
        isSingleTop: Boolean = false,
    )

    suspend fun switch(switchRootDestination: SwitchRootDestination)
}

sealed class NavigationIntent {
    data class NavigateBack(
        val route: String? = null,
        val inclusive: Boolean = false,
    ) : NavigationIntent()

    data class NavigateTo(
        val destination: DestinationScreen,
        val popUpToRoute: String? = null,
        val inclusive: Boolean = false,
        val isSingleTop: Boolean = false,
    ) : NavigationIntent()

    data class SwitchRootScreen(val destination: SwitchRootDestination) : NavigationIntent()
}

enum class SwitchRootDestination() {
    Home,
    Auth
}