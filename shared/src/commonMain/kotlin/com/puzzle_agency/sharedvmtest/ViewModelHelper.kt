package com.puzzle_agency.sharedvmtest

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ViewModelHelper : KoinComponent {
    val sharedViewModel: SharedViewModel by inject()
}