@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    jvm()
    ios()

    sourceSets.commonMain {
        dependencies {
            implementation(libs.kotlin.coroutines)
            implementation(libs.kotlinx.serialization)
            implementation(libs.multiplatform.paging.common)
        }
    }
}