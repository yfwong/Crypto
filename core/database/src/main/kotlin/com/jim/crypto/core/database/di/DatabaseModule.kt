package com.jim.crypto.core.database.di

import androidx.room.Room
import com.jim.crypto.core.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
  single { Room.databaseBuilder(get(), AppDatabase::class.java, "app_database").build() }
  single { get<AppDatabase>().cryptoCurrencyDao() }
  single { get<AppDatabase>().fiatCurrencyDao() }
}