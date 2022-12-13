@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    jvm()
    ios()

    sourceSets.commonMain {
        dependencies {
            implementation(libs.kotlin.coroutines)
            implementation(libs.multiplatform.paging.common)
        }
    }
}