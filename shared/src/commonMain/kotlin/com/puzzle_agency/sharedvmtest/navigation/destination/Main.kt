package com.puzzle_agency.sharedvmtest.navigation.destination

sealed class Main: DestinationScreen() {
    data object Home: Main()
}