plugins {
	kotlin("kapt")
	kotlin("android")
	id("com.android.library")
	id("dagger.hilt.android.plugin")
}

android {
	compileSdk = ConfigData.compileSdkVersion
	sourceSets["main"].manifest.srcFile("src/debug/java/AndroidManifest.xml")

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

	implementation("androidx.core:core-ktx:1.9.0")
	implementation("androidx.appcompat:appcompat:1.5.1")
	implementation(Dependencies.hilt.android)
	kapt(Dependencies.hilt.compiler)

	implementation(projects.data)
	implementation(projects.domain)
	implementation(Dependencies.androidTest.hilt)
	implementation(Dependencies.androidTest.runner)
	debugImplementation(Dependencies.androidTest.fragment)
}