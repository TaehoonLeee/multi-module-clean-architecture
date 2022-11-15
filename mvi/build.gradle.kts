plugins {
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
}

android {
	namespace = "com.example.mvi"
	compileSdk = ConfigData.compileSdkVersion

	defaultConfig {
		minSdk = ConfigData.minSdkVersion
		targetSdk = ConfigData.targetSdkVersion

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

	implementation(Dependencies.androidX.lifecycle.viewModel)
	implementation(Dependencies.kotlin.coroutine)
}