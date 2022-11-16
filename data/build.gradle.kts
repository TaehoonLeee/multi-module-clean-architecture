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

    implementation(libs.bundles.squareup)

    implementation(libs.bundles.ktor)

    implementation(libs.google.gson)

    implementation(libs.google.hilt)
    kapt(libs.google.hilt.compiler)

    implementation(libs.androidx.paging.runtime)
}