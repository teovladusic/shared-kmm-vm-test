package com.puzzle_agency.sharedvmtest

import com.puzzle_agency.sharedvmtest.navigation.AppNavigator
import com.puzzle_agency.sharedvmtest.navigation.IAppNavigator
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(viewModelModule())
}

fun viewModelModule() = module {
    factory { SharedViewModel() }
    factory { HomeViewModel(get()) }
    factory { (name: String) ->
        NameViewModel(name)
    }
    single<IAppNavigator> { AppNavigator() }
}


@Suppress("Unused")
//called by ios
fun initKoin() = initKoin {}