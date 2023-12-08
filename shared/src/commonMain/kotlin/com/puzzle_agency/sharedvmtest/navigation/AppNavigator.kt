package com.puzzle_agency.sharedvmtest.navigation

import com.puzzle_agency.sharedvmtest.CommonFlow
import com.puzzle_agency.sharedvmtest.asCommonFlow
import com.puzzle_agency.sharedvmtest.navigation.destination.MainScreenDestination
import com.puzzle_agency.sharedvmtest.navigation.destination.MainSheetDestination
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class AppNavigator : IAppNavigator {
    override val navigationChannel = Channel<NavigationIntent>(
        capacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
    )

    override val navigationFlow: CommonFlow<NavigationIntent>
        get() = navigationChannel.receiveAsFlow().asCommonFlow()

    override suspend fun navigateBack(route: String?, inclusive: Boolean) {
        navigationChannel.send(
            NavigationIntent.NavigateBack(
                route = route,
                inclusive = inclusive,
            ),
        )
    }

    override suspend fun navigateTo(
        destination: MainScreenDestination,
        popUpToRoute: String?,
        inclusive: Boolean,
        isSingleTop: Boolean,
    ) {
        navigationChannel.send(
            NavigationIntent.NavigateTo(
                destination = destination,
                popUpToRoute = popUpToRoute,
                inclusive = inclusive,
                isSingleTop = isSingleTop,
            ),
        )
    }

    override suspend fun presentSheet(
        sheetDestinationScreen: MainSheetDestination,
        popUpToRoute: String?,
        inclusive: Boolean,
        isSingleTop: Boolean,
    ) {
        navigationChannel.send(
            NavigationIntent.PresentSheet(
                sheetDestinationScreen,
                popUpToRoute,
                inclusive,
                isSingleTop
            )
        )
    }
}
