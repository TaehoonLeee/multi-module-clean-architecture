@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.multiplatform)
}

android {
    compileSdk = libs.versions.compileSdkVersion.get().toInt()
    sourceSets["main"].manifest.srcFile("src/AndroidManifest.xml")
}

kotlin {
    ios()
    android()

    sourceSets.commonMain {
        dependencies {
            implementation(projects.domain)
            implementation(compose.material)
            implementation(compose.foundation)
        }
    }
}