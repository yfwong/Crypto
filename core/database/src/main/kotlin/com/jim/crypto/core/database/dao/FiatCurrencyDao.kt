package com.jim.crypto.core.database.dao

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jim.crypto.core.database.model.FiatCurrencyEntity
import com.jim.crypto.core.model.data.CurrencyInfo
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
  fun getPagedItems(query: String): PagingSource<Int, FiatCurrencyEntity>

  @Query("SELECT * FROM fiat_currency")
  fun getFiatCurrencies(): Flow<List<FiatCurrencyEntity>>

  @Query(
    """
        SELECT * FROM fiat_currency
        WHERE name LIKE :query || '%'
        OR name LIKE '% ' || :query || '%'
        OR symbol LIKE :query || '%'
        """
  )
  fun searchFiatCurrencies(query: String): Flow<List<FiatCurrencyEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertFiatCurrencies(currencies: List<FiatCurrencyEntity>)

  @Query("DELETE FROM fiat_currency")
  suspend fun deleteFiatCurrencies()
}
