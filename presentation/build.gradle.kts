@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.jetbrains.compose)
	alias(libs.plugins.kotlin.multiplatform)
}

android {
	compileSdk = libs.versions.compileSdkVersion.get().toInt()

	defaultConfig {
		minSdk = libs.versions.minSdkVersion.get().toInt()
		targetSdk = libs.versions.targetSdkVersion.get().toInt()
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
			implementation(projects.features.item)
			implementation(projects.features.gallery)

			implementation(compose.foundation)
			implementation(compose.material)

			implementation(libs.koin)
			implementation(libs.kotlin.coroutines)
			implementation(libs.bundles.decompose)
		}
	}
}