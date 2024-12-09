package com.jim.crypto.core.domain.di

import com.jim.crypto.core.domain.usecase.SearchAllCurrencyListUseCase
import com.jim.crypto.core.domain.usecase.SearchCryptoCurrencyListUseCase
import com.jim.crypto.core.domain.usecase.SearchFiatCurrencyListUseCase
import org.koin.dsl.module

val domainModule = module {
  factory { SearchCryptoCurrencyListUseCase(get()) }
  factory { SearchFiatCurrencyListUseCase(get()) }
  factory { SearchAllCurrencyListUseCase(get()) }
}