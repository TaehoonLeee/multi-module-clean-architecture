@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    ios()
    android()

    sourceSets {
        commonMain.get().dependencies {
            implementation(libs.kotlin.coroutines)
            implementation(libs.multiplatform.imageloader)
            implementation(libs.multiplatform.paging.common)

            implementation(compose.foundation)
        }

        getByName("androidMain").dependencies {
            compileOnly(libs.androidx.lifecycle.viewmodel)
            compileOnly(libs.androidx.lifecycle.viewmodel.ktx)
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