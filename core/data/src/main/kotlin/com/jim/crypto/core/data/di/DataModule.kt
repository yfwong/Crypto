package com.jim.crypto.core.data.di

import com.jim.crypto.core.data.repository.CryptoCurrencyRepository
import com.jim.crypto.core.data.repository.FiatCurrencyRepository
import com.jim.crypto.core.data.repository.OfflineCryptoCurrencyRepository
import com.jim.crypto.core.data.repository.OfflineFiatCurrencyRepository
import com.jim.crypto.core.database.dao.CryptoCurrencyDao
import com.jim.crypto.core.database.dao.FiatCurrencyDao
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
class DataModule {

  @Factory
  fun provideCryptoCurrencyRepository(dao: CryptoCurrencyDao): CryptoCurrencyRepository =
    OfflineCryptoCurrencyRepository(dao)

  @Factory
  fun provideFiatCurrencyRepository(dao: FiatCurrencyDao): FiatCurrencyRepository =
    OfflineFiatCurrencyRepository(dao)
}