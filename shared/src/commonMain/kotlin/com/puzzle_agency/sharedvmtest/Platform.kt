package com.puzzle_agency.sharedvmtest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform