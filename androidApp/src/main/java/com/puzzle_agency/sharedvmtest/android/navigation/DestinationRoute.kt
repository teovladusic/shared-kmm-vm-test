package com.puzzle_agency.sharedvmtest.android.navigation

import com.puzzle_agency.sharedvmtest.android.destinations.DetailsSheetDestination
import com.puzzle_agency.sharedvmtest.android.destinations.HomeDestination
import com.puzzle_agency.sharedvmtest.android.destinations.NameScreenDestination
import com.puzzle_agency.sharedvmtest.navigation.destination.MainScreenDestination
import com.puzzle_agency.sharedvmtest.navigation.destination.MainSheetDestination


val MainScreenDestination.route: String
    get() = when (this) {
        MainScreenDestination.HomeScreen -> HomeDestination.route
        is MainScreenDestination.NameScreen -> NameScreenDestination(name).route
    }

val MainSheetDestination.route: String
    get() = when (this) {
        MainSheetDestination.DetailsSheet -> DetailsSheetDestination.route
    }