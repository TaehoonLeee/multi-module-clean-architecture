plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {
    api(project(":domain"))

    implementation(Dependencies.kotlin.stdLib)
    implementation(Dependencies.androidX.core)
    implementation(Dependencies.androidX.appCompat)
    implementation(Dependencies.material)
    testImplementation(Dependencies.test.junit)
    androidTestImplementation(Dependencies.androidX.junit)
    androidTestImplementation(Dependencies.androidX.espresso)

    implementation(Dependencies.kotlin.coroutine)
    implementation(Dependencies.kotlin.coroutineAndroid)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofit.gsonConverter)
    implementation(Dependencies.retrofit.scalarsConverter)

    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttp.loggingInterceptor)

    implementation(Dependencies.ktor.gson)
    implementation(Dependencies.ktor.okHttp)
    implementation(Dependencies.ktor.logging)

    implementation(Dependencies.gson)

    implementation(Dependencies.hilt.android)
    kapt(Dependencies.hilt.compiler)

    implementation(Dependencies.androidX.paging)

    implementation(Dependencies.socket)
}