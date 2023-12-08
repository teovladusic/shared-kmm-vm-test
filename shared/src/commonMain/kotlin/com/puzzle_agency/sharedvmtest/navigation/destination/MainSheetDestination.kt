package com.puzzle_agency.sharedvmtest.navigation.destination

sealed class MainSheetDestination {
    data object DetailsSheet: MainSheetDestination()
}