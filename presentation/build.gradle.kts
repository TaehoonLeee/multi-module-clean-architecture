import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.parcelize)
	alias(libs.plugins.jetbrains.compose)
	alias(libs.plugins.kotlin.multiplatform)
}

android {
	namespace = "com.example.presentation"
	compileSdk = libs.versions.compileSdkVersion.get().toInt()
	sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

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
	val xcFramework = XCFramework("Example")
	ios {
		binaries.framework {
			isStatic = true
			baseName = "Example"
			xcFramework.add(this)
		}
	}
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
		}
	}

	sourceSets.getByName("androidMain") {
		dependencies {
			implementation(libs.androidx.compat)
			implementation(libs.androidx.activity.compose)
			implementation(libs.androidx.navigation.compose)
		}
	}

	sourceSets.getByName("iosMain") {
		dependencies {
			implementation(projects.data)
		}
	}
}