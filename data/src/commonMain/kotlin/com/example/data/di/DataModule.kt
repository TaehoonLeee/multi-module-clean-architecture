package com.example.data.di

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.domain.repository.UnsplashRepository
import com.example.domain.repository.ItemRepository
import com.example.domain.interactor.GetItemListUseCase
import com.example.domain.interactor.GetSearchResultUseCase
import com.example.domain.interactor.InsertItemUseCase
import com.example.domain.interactor.ClearItemUseCase
import com.example.data.repository.UnsplashRepositoryImpl
import com.example.data.repository.ItemRepositoryImpl
import com.example.data.network.UnsplashApiExecutor
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind

expect val databaseModule: Module

val networkModule = module {
    factory {
        HttpClient {
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
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }
    factoryOf(::UnsplashApiExecutor)
}

val repositoryModule = module {
    singleOf(::UnsplashRepositoryImpl) bind UnsplashRepository::class
    singleOf(::ItemRepositoryImpl) bind ItemRepository::class
}

val interactorModule = module {
    singleOf(::GetItemListUseCase)
    singleOf(::GetSearchResultUseCase)
    singleOf(::InsertItemUseCase)
    singleOf(::ClearItemUseCase)
}

val dataModule get() = databaseModule + networkModule + repositoryModule + interactorModule

fun startKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication = org.koin.core.context.startKoin {
    modules(dataModule)
    appDeclaration()
}