package com.jim.crypto.core.data.di

import com.jim.crypto.core.data.DispatcherType
import com.jim.crypto.core.data.repository.CryptoCurrencyRepository
import com.jim.crypto.core.data.repository.CryptoCurrencyRepositoryImpl
import com.jim.crypto.core.data.repository.FiatCurrencyRepository
import com.jim.crypto.core.data.repository.FiatCurrencyRepositoryImpl
import com.jim.crypto.core.data.repository.InMemoryCurrencyRepository
import com.jim.crypto.core.data.repository.InMemoryCurrencyRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
  single<CryptoCurrencyRepository> {
    CryptoCurrencyRepositoryImpl(
      get(),
      get(named(DispatcherType.IO))
    )
  }
  single<FiatCurrencyRepository> {
    FiatCurrencyRepositoryImpl(
      get(),
      get(named(DispatcherType.IO))
    )
  }
  singleOf(::InMemoryCurrencyRepositoryImpl) { bind<InMemoryCurrencyRepository>() }
}