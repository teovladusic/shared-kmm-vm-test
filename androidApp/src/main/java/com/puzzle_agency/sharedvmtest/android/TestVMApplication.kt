package com.puzzle_agency.sharedvmtest.android

import android.app.Application
import com.puzzle_agency.sharedvmtest.initKoin
import org.koin.android.ext.koin.androidContext

class TestVMApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@TestVMApplication)
        }
    }
}