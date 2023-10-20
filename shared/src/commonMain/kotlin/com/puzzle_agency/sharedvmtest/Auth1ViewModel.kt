@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

package com.puzzle_agency.sharedvmtest

import com.puzzle_agency.navigation.IAppNavigator
import com.puzzle_agency.navigation.SwitchRootDestination
import com.puzzle_agency.navigation.destination.DestinationScreen
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Auth1ViewModel(private val appNavigator: IAppNavigator) : KMMViewModel() {
    init {
        viewModelScope.coroutineScope.launch {
            delay(2000)
            appNavigator.navigateTo(DestinationScreen.Auth2)
            delay(2000)
            appNavigator.switch(SwitchRootDestination.Home)
        }
    }
}

class Auth2ViewModel : KMMViewModel() {}

class HomeViewModel : KMMViewModel() {}