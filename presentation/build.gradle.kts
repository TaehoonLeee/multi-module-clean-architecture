@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.multiplatform)
}

android {
	compileSdk = libs.versions.compileSdkVersion.get().toInt()

	defaultConfig {
		minSdk = libs.versions.minSdkVersion.get().toInt()
		targetSdk = libs.versions.targetSdkVersion.get().toInt()
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
}

kotlin {
	ios()
	android()

	sourceSets.commonMain {
		dependencies {
			implementation(projects.domain)
			implementation(libs.bundles.decompose)
			implementation(projects.features.item)
			implementation(projects.features.gallery)
		}
	}
}