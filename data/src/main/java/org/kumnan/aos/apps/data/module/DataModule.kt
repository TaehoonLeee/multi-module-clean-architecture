package org.kumnan.aos.apps.data.module

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import okhttp3.OkHttpClient
import org.kumnan.aos.apps.data.cache.AppDatabase
import org.kumnan.aos.apps.data.cache.ItemDao
import org.kumnan.aos.apps.data.mapper.ResponseMapper
import org.kumnan.aos.apps.data.network.UnsplashService
import org.kumnan.aos.apps.data.network.ktor.KtorUnsplashService
import org.kumnan.aos.apps.data.network.ktor.ItemService
import org.kumnan.aos.apps.data.repository.KtorUnsplashRepositoryImpl
import org.kumnan.aos.apps.data.network.retrofit.factory.ResponseAdapterFactory
import org.kumnan.aos.apps.data.repository.ItemRepositoryImpl
import org.kumnan.aos.apps.domain.repository.ItemRepository
import org.kumnan.aos.apps.domain.repository.UnsplashRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(OkHttpClient())
            .addCallAdapterFactory(ResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.unsplash.com/")
            .build()
    }

    @Singleton
    @Provides
    @Named("unsplash")
    fun provideKtorUnsplashHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            defaultRequest {
                headers {
                    append("Accept-Version", "v1")
                    append(HttpHeaders.Authorization, "Client-ID ti90oMOJyxTN-gKrvE39bi6LM2tbMAdOvey4QMKES0k")
                }
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.unsplash.com"
                }
            }
            install(JsonFeature) {
                GsonSerializer()
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    @Singleton
    @Provides
    @Named("item")
    fun provideKtorItemHttpClient(): HttpClient {
        return HttpClient(OkHttp)
    }

    @Provides
    @Singleton
    fun provideItemDao(appDatabase: AppDatabase): ItemDao =
        appDatabase.itemDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.buildDatabase(context)

}

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindUnsplashService(ktorUnsplashService: KtorUnsplashService): UnsplashService

    @Binds
    @Singleton
    fun bindItemRepository(itemRepositoryImpl: ItemRepositoryImpl): ItemRepository

    @Binds
    @Singleton
    fun bindUnsplashRepository(unsplashRepositoryImpl: KtorUnsplashRepositoryImpl): UnsplashRepository
}