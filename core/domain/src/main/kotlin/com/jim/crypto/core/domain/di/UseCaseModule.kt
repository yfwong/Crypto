package com.jim.crypto.core.domain.di

import com.jim.crypto.core.domain.usecase.SearchAllCurrencyListUseCase
import com.jim.crypto.core.domain.usecase.SearchCryptoCurrencyListUseCase
import com.jim.crypto.core.domain.usecase.SearchFiatCurrencyListUseCase
import com.jim.crypto.core.domain.usecase.SearchInMemoryCurrencyListUseCase
import com.jim.crypto.core.domain.usecase.SetInMemoryCurrencyListUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
  factoryOf(::SearchCryptoCurrencyListUseCase)
  factoryOf(::SearchFiatCurrencyListUseCase)
  factoryOf(::SearchAllCurrencyListUseCase)
  factoryOf(::SearchInMemoryCurrencyListUseCase)
  factoryOf(::SetInMemoryCurrencyListUseCase)
}