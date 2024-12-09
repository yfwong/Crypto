package com.jim.crypto.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jim.crypto.core.database.dao.CryptoCurrencyDao
import com.jim.crypto.core.database.dao.FiatCurrencyDao
import com.jim.crypto.core.database.model.CryptoCurrencyEntity
import com.jim.crypto.core.database.model.FiatCurrencyEntity

@Database(
  entities = [CryptoCurrencyEntity::class, FiatCurrencyEntity::class],
  version = 1,
  autoMigrations = [],
  exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun cryptoCurrencyDao(): CryptoCurrencyDao
  abstract fun fiatCurrencyDao(): FiatCurrencyDao
}