package com.puzzle_agency.sharedvmtest.navigation.destination

import com.puzzle_agency.sharedvmtest.Parcelable
import com.puzzle_agency.sharedvmtest.Parcelize

sealed class DestinationScreen {

    data object Home: DestinationScreen()

    data object Auth1: DestinationScreen()
    data class Auth2(val testObject: TestObject): DestinationScreen()
}

@Parcelize
data class TestObject(val title: String): Parcelable