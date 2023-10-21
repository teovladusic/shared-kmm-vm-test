package com.puzzle_agency.sharedvmtest.android.navigation

import com.puzzle_agency.sharedvmtest.android.destinations.Authentication1Destination
import com.puzzle_agency.sharedvmtest.android.destinations.Authentication2Destination
import com.puzzle_agency.sharedvmtest.android.destinations.HomeDestination
import com.puzzle_agency.sharedvmtest.navigation.destination.DestinationScreen


val DestinationScreen.route: String
    get() = when (this) {
        DestinationScreen.Auth1 -> Authentication1Destination.route
        is DestinationScreen.Auth2 -> Authentication2Destination(testObject).route
        DestinationScreen.Home -> HomeDestination.route
    }