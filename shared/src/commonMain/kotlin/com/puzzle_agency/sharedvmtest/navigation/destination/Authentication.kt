package com.puzzle_agency.sharedvmtest.navigation.destination

import com.puzzle_agency.sharedvmtest.Parcelable
import com.puzzle_agency.sharedvmtest.Parcelize

sealed class Authentication: DestinationScreen() {
    data object Auth1: Authentication()
    data class Auth2(val testObject: TestObject): Authentication()
}

@Parcelize
data class TestObject(val title: String): Parcelable