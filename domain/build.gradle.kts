plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Dependencies.kotlin.coroutine)
    implementation(Dependencies.androidX.paging.common)
    implementation(Dependencies.jsr330)
}