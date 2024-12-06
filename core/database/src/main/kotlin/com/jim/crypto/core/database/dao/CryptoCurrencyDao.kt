package com.jim.crypto.core.database.dao

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jim.crypto.core.database.model.CryptoCurrencyEntity
import com.jim.crypto.core.model.data.CurrencyInfo
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
  fun getPagedItems(query: String): PagingSource<Int, CryptoCurrencyEntity>

  @Query("SELECT * FROM crypto_currency")
  fun getCryptoCurrencies(): Flow<List<CryptoCurrencyEntity>>

  @Query(
    """
        SELECT * FROM crypto_currency
        WHERE name LIKE :query || '%'
        OR name LIKE '% ' || :query || '%' 
        OR symbol LIKE :query || '%'
        """
  )
  fun searchCryptoCurrencies(query: String): Flow<List<CryptoCurrencyEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertCryptoCurrencies(currencies: List<CryptoCurrencyEntity>)

  @Query("DELETE FROM crypto_currency")
  suspend fun deleteCryptoCurrencies()
}
