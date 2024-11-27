package com.jim.crypto.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.jim.crypto.core.database.model.FiatCurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FiatCurrencyDao {

  @Transaction
  @Query("SELECT * FROM fiat_currency")
  fun getFiatCurrencies(): Flow<List<FiatCurrencyEntity>>

  @Upsert
  suspend fun upsertFiatCurrencies(currencies: List<FiatCurrencyEntity>)

  @Query("DELETE FROM fiat_currency")
  suspend fun deleteFiatCurrencies()
}