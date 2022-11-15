@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	kotlin("kapt")
	kotlin("android")
	alias(libs.plugins.hilt)
	alias(libs.plugins.android.library)
}

android {
	compileSdk = libs.versions.compileSdkVersion.get().toInt()
	sourceSets["main"].manifest.srcFile("src/debug/java/AndroidManifest.xml")

	defaultConfig {
		minSdk = libs.versions.minSdkVersion.get().toInt()
		targetSdk = libs.versions.targetSdkVersion.get().toInt()

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

	implementation(libs.androidx.core)
	implementation(libs.androidx.compat)
	implementation(libs.google.hilt)
	kapt(libs.google.hilt.compiler)

	implementation(projects.data)
	implementation(projects.domain)
	implementation(libs.google.hilt.test)
	implementation(libs.androidx.test.runner)
	debugImplementation(libs.androidx.test.fragment)
}