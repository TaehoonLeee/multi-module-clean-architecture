plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
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
    implementation(project(":domain"))

    with(Dependencies.kotlin) {
        implementation(stdLib)
        implementation(coroutine)
        implementation(coroutineAndroid)
    }

    with(Dependencies.androidX) {
        implementation(core)
        implementation(appCompat)

        implementation(room)
        implementation(room.ktx)
        annotationProcessor(room.compiler)
        kapt(room.compiler)

        androidTestImplementation(junit)
        androidTestImplementation(espresso)
    }

    implementation(Dependencies.material)
    testImplementation(Dependencies.test.junit)
    androidTestImplementation(Dependencies.androidTest.hilt)
    kaptAndroidTest(Dependencies.hilt.compiler)

    with(Dependencies.retrofit) {
        implementation(this)
        implementation(gsonConverter)
        implementation(scalarsConverter)
    }

    with(Dependencies.okHttp) {
        implementation(this)
        implementation(loggingInterceptor)
    }

    with(Dependencies.ktor) {
        implementation(gson)
        implementation(okHttp)
        implementation(logging)
    }

    implementation(Dependencies.gson)

    with(Dependencies.hilt) {
        implementation(android)
        kapt(compiler)
    }

    implementation(Dependencies.androidX.paging.runtime)

    implementation(Dependencies.socket)
}