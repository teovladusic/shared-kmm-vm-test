@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

package com.puzzle_agency.sharedvmtest

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.asStateFlow
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SharedViewModel: KMMViewModel() {

    private val _state = MutableStateFlow(viewModelScope = viewModelScope, TestState())

    @NativeCoroutinesState
    val state = _state.asStateFlow()

    private val channel = Channel<Boolean>()

    @NativeCoroutines
    val oneTimeEventFlow = channel.receiveAsFlow()

    init {
        viewModelScope.coroutineScope.launch {
            while (true) {
                delay(2000)
                _state.update { it.copy(isLoading = !it.isLoading) }
            }
        }

        viewModelScope.coroutineScope.launch {
            delay(5_000)
            channel.send(true)
        }
    }
}

data class TestState(
    val isLoading: Boolean = false
)