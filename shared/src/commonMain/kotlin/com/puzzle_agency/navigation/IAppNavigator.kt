package com.puzzle_agency.navigation

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

object DestinationScreenRoutes {
    const val HOME = "home"
    const val AUTH1 = "auth1"
    const val AUTH2 = "auth2"
}

enum class DestinationScreen(val route: String) {
    Home(DestinationScreenRoutes.HOME),
    Auth1(DestinationScreenRoutes.AUTH1),
    Auth2(DestinationScreenRoutes.AUTH2)
}

enum class SwitchRootDestination() {
    Home,
    Auth
}