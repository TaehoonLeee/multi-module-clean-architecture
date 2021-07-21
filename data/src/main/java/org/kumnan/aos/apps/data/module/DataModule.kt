package org.kumnan.aos.apps.data.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.kumnan.aos.apps.data.api.UnsplashService
import org.kumnan.aos.apps.data.mapper.ResponseMapper
import org.kumnan.aos.apps.data.repository.UnsplashRepositoryImpl
import org.kumnan.aos.apps.data.retrofit.ResponseAdapterFactory
import org.kumnan.aos.apps.domain.repository.UnsplashRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideUnsplashService(retrofit: Retrofit): UnsplashService {
        return retrofit.create(UnsplashService::class.java)
    }

    @Singleton
    @Provides
    fun provideUnsplashRepository(unsplashService: UnsplashService): UnsplashRepository {
        return UnsplashRepositoryImpl(unsplashService, ResponseMapper::responseToPhotoList)
    }
}