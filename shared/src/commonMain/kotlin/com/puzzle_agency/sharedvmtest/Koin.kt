package com.puzzle_agency.sharedvmtest

import com.puzzle_agency.navigation.AppNavigator
import com.puzzle_agency.navigation.IAppNavigator
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(viewModelModule())
}

fun viewModelModule() = module {
    factory { SharedViewModel() }
    factory { Auth1ViewModel() }
    factory { Auth2ViewModel() }
    factory { HomeViewModel() }
    single<IAppNavigator> { AppNavigator() }
}


//called by ios
fun initKoin() = initKoin {}