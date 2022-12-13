package com.example.data.di

import com.example.data.cache.DatabaseDriverFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

actual val databaseModule = module {
    factoryOf(::DatabaseDriverFactory)
}