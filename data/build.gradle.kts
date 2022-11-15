plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = libs.versions.compileSdkVersion.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdkVersion.get().toInt()
        targetSdk = libs.versions.targetSdkVersion.get().toInt()
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
    implementation(projects.domain)

    with(libs.androidx) {
        implementation(room)
        implementation(room.runtime)
        annotationProcessor(room.compiler)
        kapt(room.compiler)

        androidTestImplementation(test.junit)
        androidTestImplementation(test.espresso)
    }

    testImplementation(libs.junit)
    androidTestImplementation(libs.google.hilt.test)
    kaptAndroidTest(libs.google.hilt.compiler)

    with(libs.squareup) {
        implementation(retrofit)
        implementation(retrofit.gson.converter)
        implementation(retrofit.scalars.converter)
        implementation(okhttp)
        implementation(okhttp.logging.interceptor)
    }

    with(libs.ktor) {
        implementation(gson)
        implementation(okhttp)
        implementation(logging)
    }

    implementation(libs.google.gson)

    implementation(libs.google.hilt)
    kapt(libs.google.hilt.compiler)

    implementation(libs.androidx.paging.runtime)
}