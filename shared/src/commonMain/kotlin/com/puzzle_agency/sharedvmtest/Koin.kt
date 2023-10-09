package com.puzzle_agency.sharedvmtest

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(viewModelModule())
}

fun viewModelModule() = module {
    factory { SharedViewModel() }
}


//called by ios
fun initKoin() = initKoin {}