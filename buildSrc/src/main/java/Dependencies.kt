object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val hilt by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
    val ksp by lazy { "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:${Versions.ksp}" }
}

object Dependencies {
    val kotlin = Kotlin
    val hilt = Hilt
    val androidX = AndroidX
    val retrofit = Retrofit()
    val okHttp = OkHttp()
    val ktor = Ktor
    val test = Test
    val androidTest = AndroidTest
    val glide = Glide()

    val material by lazy { "com.google.android.material:material:1.4.0" }
    val jsr330 by lazy { "javax.inject:javax.inject:1" }
    val socket by lazy { "io.socket:socket.io-client:1.0.0" }
    val gson by lazy { "com.google.code.gson:gson:${Versions.gson}" }

    object Kotlin {
        val stdLib by lazy { "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}" }
        val coroutine by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutineCore}" }
        val coroutineAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineAndroid}" }
    }

    object Hilt {
        val android by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
        val compiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }
    }

    object AndroidX {
        val core by lazy { "androidx.core:core-ktx:1.6.0" }
        val appCompat by lazy { "androidx.appcompat:appcompat:1.3.0" }
        val paging by lazy { "androidx.paging:paging-runtime-ktx:${Versions.paging}" }
        val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:2.0.4" }

        val junit by lazy { "androidx.test.ext:junit:1.1.3" }
        val espresso by lazy { "androidx.test.espresso:espresso-core:3.3.0" }

        val lifecycle = Lifecycle
        object Lifecycle {
            val runtime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}" }
            val viewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}" }
            val liveData by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}" }
        }

        val navigation = Navigation
        object Navigation {
            val fragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}" }
            val ui by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigation}" }
        }

        val room = Room()
        class Room(
            private val name: String = "androidx.room:room-runtime:${Versions.room}"
        ) : CharSequence by name {
            val ktx by lazy { "androidx.room:room-ktx:${Versions.room}" }
            val compiler by lazy { "androidx.room:room-compiler:${Versions.room}" }
            
            override fun toString(): String = name
        }
    }

    class Retrofit(
        private val name: String = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    ) : CharSequence by name {
        val gsonConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
        val scalarsConverter by lazy { "com.squareup.retrofit2:converter-scalars:${Versions.retrofit}" }

        override fun toString() = name
    }

    class OkHttp(
        private val name: String = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    ) : CharSequence by name {
        val loggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}" }

        override fun toString() = name
    }

    object Ktor {
        val gson by lazy { "io.ktor:ktor-client-gson:${Versions.ktor}" }
        val okHttp by lazy { "io.ktor:ktor-client-okhttp:${Versions.ktor}" }
        val logging by lazy { "io.ktor:ktor-client-logging-jvm:${Versions.ktor}" }
    }

    object Test {
        val junit by lazy { "junit:junit:4.13.2" }
        val robolectric by lazy { "org.robolectric:robolectric:4.6.1" }
        val archCore by lazy { "androidx.arch.core:core-testing:2.1.0" }
        val mockito by lazy { "org.mockito:mockito-core:3.8.0" }
        val coroutine by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1" }
    }

    object AndroidTest {
        val runner by lazy { "androidx.test:runner:1.4.0" }
        val rules by lazy { "androidx.test:rules:1.4.0" }
        val archCore by lazy { "androidx.arch.core:core-testing:2.1.0" }
        val junit by lazy { "androidx.test.ext:junit-ktx:1.1.3" }
        val espressoCore by lazy { "androidx.test.espresso:espresso-core:3.3.0" }
        val espressoContribute by lazy { "androidx.test.espresso:espresso-contrib:3.3.0" }
        val mockito by lazy { "org.mockito:mockito-android:3.8.0" }
        val hilt by lazy { "com.google.dagger:hilt-android-testing:${Versions.hilt}" }
        val fragment by lazy { "androidx.fragment:fragment-testing:1.3.6" }
    }

    class Glide(
        private val name: String = "com.github.bumptech.glide:glide:${Versions.glide}"
    ) : CharSequence by name {
        val webDecoder by lazy { "com.github.zjupure:webpdecoder:2.0.${Versions.glide}" }

        override fun toString() = name
    }
}