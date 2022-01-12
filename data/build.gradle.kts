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

    implementation(Dependencies.androidX.paging)

    implementation(Dependencies.socket)
}