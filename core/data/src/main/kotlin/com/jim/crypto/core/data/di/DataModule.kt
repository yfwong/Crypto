package com.jim.crypto.core.data.di

import com.jim.crypto.core.data.repository.CryptoCurrencyRepository
import com.jim.crypto.core.data.repository.FiatCurrencyRepository
import com.jim.crypto.core.data.repository.OfflineCryptoCurrencyRepository
import com.jim.crypto.core.data.repository.OfflineFiatCurrencyRepository
import org.koin.dsl.module

val dataModule = module {
  single<CryptoCurrencyRepository> { OfflineCryptoCurrencyRepository(get()) }
  single<FiatCurrencyRepository> { OfflineFiatCurrencyRepository(get()) }
}