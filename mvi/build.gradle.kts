@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin)
}

android {
	namespace = "com.example.mvi"
	compileSdk = libs.versions.compileSdkVersion.get().toInt()

	defaultConfig {
		minSdk = libs.versions.minSdkVersion.get().toInt()
		targetSdk = libs.versions.targetSdkVersion.get().toInt()
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

	compileOnly(libs.androidx.lifecycle.viewmodel)
	compileOnly(libs.kotlin.coroutines)
}