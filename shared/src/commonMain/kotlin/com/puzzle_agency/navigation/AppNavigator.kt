package com.puzzle_agency.navigation

import com.puzzle_agency.sharedvmtest.CommonFlow
import com.puzzle_agency.sharedvmtest.asCommonFlow
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

    override fun tryNavigateBack(route: String?, inclusive: Boolean) {
        navigationChannel.trySend(
            NavigationIntent.NavigateBack(
                route = route,
                inclusive = inclusive,
            ),
        )
    }

    override suspend fun navigateTo(
        destination: DestinationScreen,
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

    override fun tryNavigateTo(
        destination: DestinationScreen,
        popUpToRoute: String?,
        inclusive: Boolean,
        isSingleTop: Boolean,
    ) {
        navigationChannel.trySend(
            NavigationIntent.NavigateTo(
                destination = destination,
                popUpToRoute = popUpToRoute,
                inclusive = inclusive,
                isSingleTop = isSingleTop,
            ),
        )
    }

    override suspend fun switch(switchRootDestination: SwitchRootDestination) {
        navigationChannel.send(NavigationIntent.SwitchRootScreen(switchRootDestination))
    }
}
