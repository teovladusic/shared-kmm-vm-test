plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.google.devtools.ksp") version "1.9.10-1.0.13"
    id("com.rickclephas.kmp.nativecoroutines") version "1.0.0-ALPHA-18"
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                api("com.rickclephas.kmm:kmm-viewmodel-core:1.0.0-ALPHA-14")
                implementation("io.insert-koin:koin-core:3.4.3")
                implementation("io.ktor:ktor-client-core:2.3.4")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
}

android {
    namespace = "com.puzzle_agency.sharedvmtest"
    compileSdk = 33
    defaultConfig {
        minSdk = 26
    }
}
