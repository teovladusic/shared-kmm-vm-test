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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class SharedViewModel: KMMViewModel() {

    private val _state = MutableStateFlow(viewModelScope = viewModelScope, TestState())

    @NativeCoroutinesState
    val state = _state.asStateFlow()

    private val channel = Channel<Boolean>()

    @NativeCoroutines
    val oneTimeEventFlow = channel.receiveAsFlow().asCommonFlow()

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

class Event<out T>(
    private val value: T
) {
    private var canConsume = true
    fun takeValue() = value

    fun consume(block: (T) -> Unit) {
        if (this.canConsume) {
            canConsume = false
            block(value)
        }
    }
}

fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)
class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job()

        onEach {
            block(it)
        }.launchIn(CoroutineScope(Dispatchers.Main + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}
