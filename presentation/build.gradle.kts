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
	implementation(projects.domain)
	implementation(projects.features.gallery)
	implementation(projects.features.item)

	implementation(Dependencies.androidX.core)
	implementation(Dependencies.androidX.appCompat)
	implementation(Dependencies.material)
	implementation(Dependencies.androidX.constraintLayout)

	implementation(Dependencies.hilt.android)
	kapt(Dependencies.hilt.compiler)

	implementation(Dependencies.androidX.navigation.fragment)
	implementation(Dependencies.androidX.navigation.ui)
}