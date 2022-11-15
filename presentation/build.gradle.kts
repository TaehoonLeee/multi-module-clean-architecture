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

	implementation(libs.androidx.core)
	implementation(libs.androidx.compat)
	implementation(libs.google.material)
	implementation(libs.androidx.constraintlayout)

	implementation(libs.google.hilt)
	kapt(libs.google.hilt.compiler)

	implementation(libs.androidx.navigation.fragment)
	implementation(libs.androidx.navigation.ui)
}