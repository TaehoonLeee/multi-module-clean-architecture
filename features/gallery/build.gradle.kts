plugins {
    kotlin("kapt")
    kotlin("android")
    id("com.android.library")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
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
    implementation(project(":domain"))

    with(Dependencies.androidX) {
        implementation(core)
        implementation(appCompat)
        implementation(lifecycle.runtime)
        implementation(lifecycle.viewModel)
        implementation(lifecycle.liveData)
        implementation(navigation.fragment)
    }

    implementation(Dependencies.material)

    implementation(Dependencies.glide)
    implementation(Dependencies.glide.webDecoder)

    with(Dependencies.hilt) {
        implementation(android)
        kapt(compiler)
    }

    implementation(Dependencies.androidX.paging.runtime)

    androidTestImplementation(project(":common"))
    androidTestImplementation(Dependencies.androidTest.archCore)
    androidTestImplementation(Dependencies.androidTest.junit)
    androidTestImplementation(Dependencies.androidTest.espressoCore)
    androidTestImplementation(Dependencies.androidTest.espressoContribute)
    androidTestImplementation(Dependencies.androidTest.mockito)
    androidTestImplementation(Dependencies.androidTest.hilt)
    kaptAndroidTest(Dependencies.hilt.compiler)

    testImplementation(Dependencies.test.junit)
    testImplementation(Dependencies.test.mockito)
    testImplementation(Dependencies.test.coroutine)
    testImplementation(Dependencies.test.robolectric)
}