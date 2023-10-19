package com.puzzle_agency.sharedvmtest.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.puzzle_agency.navigation.DestinationScreen
import com.puzzle_agency.navigation.DestinationScreenRoutes
import com.puzzle_agency.navigation.IAppNavigator
import com.puzzle_agency.sharedvmtest.Auth1ViewModel
import com.puzzle_agency.sharedvmtest.Auth2ViewModel
import com.puzzle_agency.sharedvmtest.HomeViewModel
import com.puzzle_agency.sharedvmtest.android.navigation.NavigationEffects
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    private val appNavigator: IAppNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            NavigationEffects(
                navigationChannel = appNavigator.navigationChannel,
                navHostController = navController
            )

            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        navController = navController
                    )
                }
            }
        }
    }
}

@RootNavGraph(start = true)
@Destination(route = DestinationScreenRoutes.AUTH1)
@Composable
fun Authentication1(viewModel: Auth1ViewModel = koinViewModel()) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Auth 1", modifier = Modifier.align(Alignment.Center))
    }
}

@Destination(DestinationScreenRoutes.AUTH2)
@Composable
fun Authentication2(viewModel: Auth2ViewModel = koinViewModel()) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Auth 2", modifier = Modifier.align(Alignment.Center))
    }
}


@Destination(DestinationScreenRoutes.HOME)
@Composable
fun Home(viewModel: HomeViewModel = koinViewModel()) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Home", modifier = Modifier.align(Alignment.Center))
    }
}