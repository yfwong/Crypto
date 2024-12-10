package com.jim.crypto.core.data.di

import com.jim.crypto.core.data.repository.CryptoCurrencyRepository
import com.jim.crypto.core.data.repository.FiatCurrencyRepository
import com.jim.crypto.core.data.repository.InMemoryCurrencyRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
  singleOf(::CryptoCurrencyRepository)
  singleOf(::FiatCurrencyRepository)
  singleOf(::InMemoryCurrencyRepository)
}