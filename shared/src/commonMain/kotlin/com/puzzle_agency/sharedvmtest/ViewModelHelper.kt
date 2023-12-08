package com.puzzle_agency.sharedvmtest

import com.puzzle_agency.sharedvmtest.navigation.IAppNavigator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ViewModelHelper : KoinComponent {
    val sharedViewModel: SharedViewModel by inject()
    val homeViewModel: HomeViewModel by inject()
}

class Helper: KoinComponent {
    val appNavigator: IAppNavigator by inject()
}