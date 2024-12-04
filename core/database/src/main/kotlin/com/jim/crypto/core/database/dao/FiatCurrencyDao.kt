package com.jim.crypto.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.jim.crypto.core.database.model.FiatCurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FiatCurrencyDao {

  @Transaction
  @Query("SELECT * FROM fiat_currency WHERE name LIKE :query")
  fun getFiatCurrencies(query: String): Flow<List<FiatCurrencyEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertFiatCurrencies(currencies: List<FiatCurrencyEntity>)

  @Query("DELETE FROM fiat_currency")
  suspend fun deleteFiatCurrencies()
}
