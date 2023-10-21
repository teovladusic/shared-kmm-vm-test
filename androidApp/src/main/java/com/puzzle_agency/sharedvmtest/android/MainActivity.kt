package com.puzzle_agency.sharedvmtest.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import androidx.navigation.plusAssign
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.puzzle_agency.sharedvmtest.Auth1ViewModel
import com.puzzle_agency.sharedvmtest.Auth2ViewModel
import com.puzzle_agency.sharedvmtest.HomeViewModel
import com.puzzle_agency.sharedvmtest.android.navigation.NavigationEffects
import com.puzzle_agency.sharedvmtest.navigation.IAppNavigator
import com.puzzle_agency.sharedvmtest.navigation.destination.TestObject
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.spec.DestinationStyle
import com.ramcosta.composedestinations.spec.DestinationStyleBottomSheet
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    private val appNavigator: IAppNavigator by inject()

    @OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val bottomSheetNavigator = rememberBottomSheetNavigator()
            navController.navigatorProvider += bottomSheetNavigator

            NavigationEffects(
                navigationChannel = appNavigator.navigationChannel,
                navHostController = navController
            )

            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ModalBottomSheetLayout(
                        bottomSheetNavigator = bottomSheetNavigator
                    ) {
                        DestinationsNavHost(
                            engine = rememberAnimatedNavHostEngine(),
                            navController = navController,
                            navGraph = NavGraphs.root
                        )
                    }
                }
            }
        }
    }
}

@RootNavGraph(start = true)
@Destination
@Composable
fun Authentication1(viewModel: Auth1ViewModel = koinViewModel()) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Auth 1", modifier = Modifier.align(Alignment.Center))


    }
}

@Destination(navArgsDelegate = Auth2Args::class)
@Composable
fun Authentication2(
    viewModel: Auth2ViewModel = koinViewModel(),
    args: Auth2Args
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = args.testObject.title, modifier = Modifier.align(Alignment.Center))
    }
}

data class Auth2Args(val testObject: TestObject)

@Destination
@Composable
fun Home(viewModel: HomeViewModel = koinViewModel()) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Home", modifier = Modifier.align(Alignment.Center))
    }
}

@Destination(style = DestinationStyleBottomSheet::class)
@Composable
fun RandomSheet() {
    Column(modifier = Modifier.fillMaxSize().background(Color.Red)) {
        Text(text = "Random sheet")
    }
}