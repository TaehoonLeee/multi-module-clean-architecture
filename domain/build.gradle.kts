plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation(Dependencies.kotlin.stdLib)

    // Coroutine
    implementation(Dependencies.kotlin.coroutine)
    implementation(Dependencies.kotlin.coroutineAndroid)

    implementation(Dependencies.javaInject)
}