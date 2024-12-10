package com.jim.crypto.core.data.di

import com.jim.crypto.core.data.DispatcherType
import com.jim.crypto.core.data.repository.CryptoCurrencyRepository
import com.jim.crypto.core.data.repository.FiatCurrencyRepository
import com.jim.crypto.core.data.repository.InMemoryCurrencyRepository
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
  single { CryptoCurrencyRepository(get(), get(named(DispatcherType.IO))) }
  single { FiatCurrencyRepository(get(), get(named(DispatcherType.IO))) }
  singleOf(::InMemoryCurrencyRepository)
}