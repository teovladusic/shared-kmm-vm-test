package com.puzzle_agency.sharedvmtest.navigation

import com.puzzle_agency.sharedvmtest.CommonFlow
import com.puzzle_agency.sharedvmtest.navigation.destination.MainScreenDestination
import com.puzzle_agency.sharedvmtest.navigation.destination.MainSheetDestination
import kotlinx.coroutines.channels.Channel

interface IAppNavigator {
    val navigationChannel: Channel<NavigationIntent>
    val navigationFlow: CommonFlow<NavigationIntent>

    suspend fun navigateBack(
        route: String? = null,
        inclusive: Boolean = false,
    )

    suspend fun navigateTo(
        destination: MainScreenDestination,
        popUpToRoute: String? = null,
        inclusive: Boolean = false,
        isSingleTop: Boolean = false,
    )

    suspend fun presentSheet(
        sheetDestinationScreen: MainSheetDestination,
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
        val destination: MainScreenDestination,
        val popUpToRoute: String? = null,
        val inclusive: Boolean = false,
        val isSingleTop: Boolean = false,
    ) : NavigationIntent()

    data class PresentSheet(
        val destination: MainSheetDestination,
        val popUpToRoute: String? = null,
        val inclusive: Boolean = false,
        val isSingleTop: Boolean = false,
    ) : NavigationIntent()
}