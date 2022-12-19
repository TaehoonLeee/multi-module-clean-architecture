package com.example.domain.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.domain.interactor.GetItemListUseCase
import com.example.domain.interactor.GetSearchResultUseCase
import com.example.domain.interactor.InsertItemUseCase
import com.example.domain.interactor.ClearItemUseCase

val interactorModule = module {
	singleOf(::GetItemListUseCase)
	singleOf(::GetSearchResultUseCase)
	singleOf(::InsertItemUseCase)
	singleOf(::ClearItemUseCase)
}

val domainModule = interactorModule