package com.jim.crypto.core.domain.di

import com.jim.crypto.core.domain.SearchCryptoCurrencyListUseCase
import com.jim.crypto.core.domain.SearchFiatCurrencyListUseCase
import com.jim.crypto.core.domain.SearchInMemoryCurrencyListUseCase
import com.jim.crypto.core.domain.SetInMemoryCurrencyListUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
  factoryOf(::SearchCryptoCurrencyListUseCase)
  factoryOf(::SearchFiatCurrencyListUseCase)
  factoryOf(::SearchInMemoryCurrencyListUseCase)
  factoryOf(::SetInMemoryCurrencyListUseCase)
}