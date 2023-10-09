package com.puzzle_agency.sharedvmtest.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.puzzle_agency.sharedvmtest.SharedViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val viewModel: SharedViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val state by viewModel.state.collectAsState()

                    Column(modifier = Modifier.fillMaxSize()) {
                        if (state.isLoading) {
                            CircularProgressIndicator()
                        } else {
                            Text(text = "No loading")
                        }
                    }
                }
            }
        }
    }
}