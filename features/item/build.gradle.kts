@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("kapt")
    kotlin("android")
    alias(libs.plugins.hilt)
    alias(libs.plugins.android.library)
}

android {
    compileSdk = libs.versions.compileSdkVersion.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdkVersion.get().toInt()
        targetSdk = libs.versions.targetSdkVersion.get().toInt()
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
        freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(projects.domain)
    implementation(projects.mvi)

    with(libs.androidx) {
        implementation(core)
        implementation(compat)
        implementation(lifecycle.runtime)
        implementation(lifecycle.viewmodel)
        implementation(lifecycle.livedata)
        implementation(navigation.fragment)
        implementation(fragment)
    }

    implementation(libs.google.material)

    implementation(libs.glide)
    implementation(libs.glide.webdecoder)

    implementation(libs.google.hilt)
    kapt(libs.google.hilt.compiler)

    implementation(libs.androidx.paging.runtime)

    androidTestImplementation(projects.common)
    androidTestImplementation(libs.androidx.test.arch.core)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
    androidTestImplementation(libs.androidx.test.espresso.contrib)
    androidTestImplementation(libs.mockito.android)
    androidTestImplementation(libs.google.hilt.test)
    kaptAndroidTest(libs.google.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.robolectric)
}