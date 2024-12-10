package com.jim.crypto.core.domain.di

import com.jim.crypto.core.domain.SearchCurrencyListUseCase
import com.jim.crypto.core.domain.SetCurrencyListUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
  factoryOf(::SearchCurrencyListUseCase)
  factoryOf(::SetCurrencyListUseCase)
}