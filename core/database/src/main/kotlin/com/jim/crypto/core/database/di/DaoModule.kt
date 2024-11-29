package com.jim.crypto.core.database.di

import com.jim.crypto.core.database.AppDatabase
import com.jim.crypto.core.database.dao.CryptoCurrencyDao
import com.jim.crypto.core.database.dao.FiatCurrencyDao
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class DaoModule {

  @Single
  fun provideCryptoCurrencyDao(db: AppDatabase): CryptoCurrencyDao = db.cryptoCurrencyDao()

  @Single
  fun provideFiatCurrencyDao(db: AppDatabase): FiatCurrencyDao = db.fiatCurrencyDao()
}