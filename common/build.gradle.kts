@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    ios()
    android()

    sourceSets.commonMain {
        dependencies {
            implementation(libs.ktor.core)
            implementation(libs.kotlin.coroutines)
            implementation(libs.multiplatform.paging.common)

            implementation(compose.foundation)

            implementation(libs.bundles.decompose)
        }
    }

    sourceSets {
        getByName("androidMain") {
            dependencies {
                implementation(libs.ktor.okhttp)
            }
        }
        getByName("iosMain") {
            dependencies {
                implementation(libs.ktor.darwin)
            }
        }
    }
}

android {
    namespace = "com.example.common"
    compileSdk = libs.versions.compileSdkVersion.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdkVersion.get().toInt()
        targetSdk = libs.versions.targetSdkVersion.get().toInt()
    }
}