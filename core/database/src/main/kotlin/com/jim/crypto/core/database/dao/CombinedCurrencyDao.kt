package com.jim.crypto.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.jim.crypto.core.database.model.CombinedCurrencyEntity

@Dao
interface CombinedCurrencyDao {

  @Query(
    """
        SELECT id, name, symbol, NULL AS code
        FROM crypto_currency
        WHERE name LIKE :query || '%'
        OR name LIKE '% ' || :query || '%' 
        OR symbol LIKE :query || '%'
        
        UNION ALL
        
        SELECT id, name, symbol, code
        FROM fiat_currency
        WHERE name LIKE :query || '%'
        OR name LIKE '% ' || :query || '%' 
        OR symbol LIKE :query || '%'
        """
  )
  fun getPagedItems(query: String): PagingSource<Int, CombinedCurrencyEntity>
}
