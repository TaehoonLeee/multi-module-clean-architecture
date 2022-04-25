plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = "org.kumnan.aos.apps.testpractice"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName
        testInstrumentationRunner = "org.kumnan.aos.apps.testpractice.CustomJUnitRunner"
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

    implementation(project(":presentation"))
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(Dependencies.kotlin.stdLib)
    implementation(Dependencies.androidX.core)
    implementation(Dependencies.androidX.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.androidX.constraintLayout)

    testImplementation(Dependencies.test.junit)
    testImplementation(Dependencies.test.robolectric)
    testImplementation(Dependencies.test.archCore)
    testImplementation(Dependencies.test.mockito)
    testImplementation(Dependencies.test.coroutine)

    androidTestImplementation(Dependencies.androidTest.runner)
    androidTestImplementation(Dependencies.androidTest.rules)
    androidTestImplementation(Dependencies.androidTest.archCore)
    androidTestImplementation(Dependencies.androidTest.junit)
    androidTestImplementation(Dependencies.androidTest.espressoCore)
    androidTestImplementation(Dependencies.androidTest.espressoContribute)
    androidTestImplementation(Dependencies.androidTest.mockito)
    androidTestImplementation(Dependencies.androidTest.hilt)
    debugImplementation(Dependencies.androidTest.fragment)
    kaptAndroidTest(Dependencies.hilt.compiler)

    implementation(Dependencies.hilt.android)
    kapt(Dependencies.hilt.compiler)

    implementation(Dependencies.kotlin.coroutine)
    implementation(Dependencies.kotlin.coroutineAndroid)

    implementation(Dependencies.androidX.lifecycle.runtime)
    implementation(Dependencies.androidX.lifecycle.viewModel)
    implementation(Dependencies.androidX.lifecycle.liveData)

    implementation(Dependencies.androidX.navigation.fragment)
    implementation(Dependencies.androidX.navigation.ui)

    implementation(Dependencies.glide)
    implementation(Dependencies.glide.webDecoder)

    implementation(Dependencies.gson)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofit.gsonConverter)
    implementation(Dependencies.retrofit.scalarsConverter)

    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttp.loggingInterceptor)

    implementation(Dependencies.ktor.gson)

    implementation(Dependencies.androidX.paging)
}