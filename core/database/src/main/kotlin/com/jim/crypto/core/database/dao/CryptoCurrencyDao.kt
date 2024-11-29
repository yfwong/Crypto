package com.jim.crypto.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.jim.crypto.core.database.model.CryptoCurrencyEntity

@Dao
interface CryptoCurrencyDao {

  @Transaction
  @Query("SELECT * FROM crypto_currency")
  suspend fun getCryptoCurrencies(): List<CryptoCurrencyEntity>

  @Upsert
  suspend fun upsertCryptoCurrencies(currencies: List<CryptoCurrencyEntity>)

  @Query("DELETE FROM crypto_currency")
  suspend fun deleteCryptoCurrencies()
}
