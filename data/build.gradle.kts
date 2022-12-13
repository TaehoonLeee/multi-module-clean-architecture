@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.sqldelight)
}

android {
    compileSdk = libs.versions.compileSdkVersion.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = libs.versions.minSdkVersion.get().toInt()
        targetSdk = libs.versions.targetSdkVersion.get().toInt()
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

kotlin {
    android()
    ios()

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.domain)
                implementation(libs.kotlin.coroutines)
                implementation(libs.sqldelight.coroutines)
                implementation(libs.multiplatform.paging.common)
                implementation(libs.koin)
                implementation(libs.ktor.core)
                implementation(libs.bundles.ktor)
            }
        }
        getByName("androidMain").dependencies {
            implementation(libs.ktor.okhttp)
            implementation(libs.sqldelight.android)
        }
        getByName("iosMain").dependencies {
            implementation(libs.ktor.darwin)
            implementation(libs.sqldelight.native)
            implementation(libs.multiplatform.paging.runtime)
        }
    }
}

sqldelight {
    database("ItemDatabase") {
        packageName = "com.example.data"
    }
}