package com.jim.crypto.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.jim.crypto.core.database.model.CryptoCurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoCurrencyDao {

  @Transaction
  @Query("SELECT * FROM crypto_currency WHERE name LIKE :query")
  fun getCryptoCurrencies(query: String): Flow<List<CryptoCurrencyEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertCryptoCurrencies(currencies: List<CryptoCurrencyEntity>)

  @Query("DELETE FROM crypto_currency")
  suspend fun deleteCryptoCurrencies()
}
