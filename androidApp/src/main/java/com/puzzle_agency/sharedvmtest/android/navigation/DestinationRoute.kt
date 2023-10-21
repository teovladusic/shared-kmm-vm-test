package com.puzzle_agency.sharedvmtest.android.navigation

import com.puzzle_agency.sharedvmtest.android.destinations.Authentication1Destination
import com.puzzle_agency.sharedvmtest.android.destinations.Authentication2Destination
import com.puzzle_agency.sharedvmtest.android.destinations.HomeDestination
import com.puzzle_agency.sharedvmtest.android.destinations.RandomSheetDestination
import com.puzzle_agency.sharedvmtest.navigation.destination.Authentication
import com.puzzle_agency.sharedvmtest.navigation.destination.DestinationScreen
import com.puzzle_agency.sharedvmtest.navigation.destination.Main
import com.puzzle_agency.sharedvmtest.navigation.destination.Sheet
import com.puzzle_agency.sharedvmtest.navigation.destination.SheetDestinationScreen


val DestinationScreen.route: String
    get() = when (this) {
        Authentication.Auth1 -> Authentication1Destination.route
        is Authentication.Auth2 -> Authentication2Destination(testObject).route
        Main.Home -> HomeDestination.route
    }

val SheetDestinationScreen.route: String
    get() = when (this) {
        Sheet.RandomSheet -> RandomSheetDestination.route
    }