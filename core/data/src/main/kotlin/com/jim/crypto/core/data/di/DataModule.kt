package com.jim.crypto.core.data.di

import com.jim.crypto.core.data.repository.AllCurrencyRepository
import com.jim.crypto.core.data.repository.CryptoCurrencyRepository
import com.jim.crypto.core.data.repository.DemoJsonRepository
import com.jim.crypto.core.data.repository.FiatCurrencyRepository
import org.koin.dsl.module

val dataModule = module {
  single<CryptoCurrencyRepository> { CryptoCurrencyRepository(get()) }
  single<FiatCurrencyRepository> { FiatCurrencyRepository(get()) }
  single<AllCurrencyRepository> { AllCurrencyRepository(get(), get()) }
  single<DemoJsonRepository> { DemoJsonRepository(get()) }
}