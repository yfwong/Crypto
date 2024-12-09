package com.jim.crypto.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jim.crypto.core.database.model.CryptoCurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoCurrencyDao {

  @Query(
    """
        SELECT * FROM crypto_currency
        WHERE name LIKE :query || '%'
        OR name LIKE '% ' || :query || '%'
        OR symbol LIKE :query || '%'
        """
  )
  fun getItems(query: String): Flow<List<CryptoCurrencyEntity>>

  @Query("SELECT * FROM crypto_currency")
  fun getItems(): Flow<List<CryptoCurrencyEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertItems(currencies: List<CryptoCurrencyEntity>)

  @Query("DELETE FROM crypto_currency")
  suspend fun deleteItems()
}
