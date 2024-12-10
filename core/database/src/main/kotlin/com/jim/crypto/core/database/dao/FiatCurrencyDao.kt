package com.jim.crypto.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jim.crypto.core.database.model.FiatCurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FiatCurrencyDao {

  @Query(
    """
        SELECT * FROM fiat_currency
        WHERE name LIKE :query || '%'
        OR name LIKE '% ' || :query || '%'
        OR symbol LIKE :query || '%'
        """
  )
  fun getItems(query: String): Flow<List<FiatCurrencyEntity>>

  @Query("SELECT * FROM fiat_currency")
  fun getItems(): Flow<List<FiatCurrencyEntity>>

  @Query("SELECT * FROM fiat_currency")
  suspend fun getItemsSync(): List<FiatCurrencyEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertItems(currencies: List<FiatCurrencyEntity>)

  @Query("DELETE FROM fiat_currency")
  suspend fun deleteItems()
}
