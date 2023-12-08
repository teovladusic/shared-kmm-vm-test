package com.puzzle_agency.sharedvmtest.navigation.destination

sealed class MainScreenDestination {
    data object HomeScreen: MainScreenDestination()
    data class NameScreen(val name: String): MainScreenDestination()
}