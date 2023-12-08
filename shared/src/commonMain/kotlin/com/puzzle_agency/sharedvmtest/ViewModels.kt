@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

package com.puzzle_agency.sharedvmtest

import com.puzzle_agency.sharedvmtest.navigation.IAppNavigator
import com.puzzle_agency.sharedvmtest.navigation.destination.MainScreenDestination
import com.puzzle_agency.sharedvmtest.navigation.destination.MainSheetDestination
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val appNavigator: IAppNavigator
) : KMMViewModel() {

    fun showSheet() {
        viewModelScope.coroutineScope.launch {
            appNavigator.presentSheet(MainSheetDestination.DetailsSheet)
        }
    }

    fun showScreen() {
        viewModelScope.coroutineScope.launch {
            val name = "kotlin-multiplatform-navigation"
            appNavigator.navigateTo(MainScreenDestination.NameScreen(name))
        }
    }
}

class NameViewModel(
    name: String
): KMMViewModel() {

    private val _state = MutableStateFlow(viewModelScope, "")
    val state = _state.asStateFlow()

    init {
        _state.update { name }
    }
}