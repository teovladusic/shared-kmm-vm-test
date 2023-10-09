@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

package com.puzzle_agency.sharedvmtest

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import kotlinx.coroutines.flow.asStateFlow
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SharedViewModel: KMMViewModel() {

    private val _state = MutableStateFlow(viewModelScope = viewModelScope, TestState())

    @NativeCoroutinesState
    val state = _state.asStateFlow()

    init {
        viewModelScope.coroutineScope.launch {
            while (true) {
                delay(2000)
                _state.update { it.copy(isLoading = !it.isLoading) }
            }
        }
    }
}

data class TestState(
    val isLoading: Boolean = false
)