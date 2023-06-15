@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("kapt")
    kotlin("android")
    alias(libs.plugins.hilt)
    alias(libs.plugins.google.service)
    alias(libs.plugins.android.application)
}

android {
    compileSdk = libs.versions.compileSdkVersion.get().toInt()

    defaultConfig {
        applicationId = "com.example.testpractice"
        minSdk = libs.versions.minSdkVersion.get().toInt()
        targetSdk = libs.versions.targetSdkVersion.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
        testInstrumentationRunner = "com.example.testpractice.CustomJUnitRunner"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(projects.presentation)
    implementation(projects.domain)
    implementation(projects.data)

    implementation(libs.androidx.compat)
    implementation(libs.google.hilt)
    kapt(libs.google.hilt.compiler)

    implementation(platform("com.google.firebase:firebase-bom:32.1.1"))
}