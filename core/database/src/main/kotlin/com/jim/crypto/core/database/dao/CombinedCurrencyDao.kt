package com.jim.crypto.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.jim.crypto.core.database.model.CombinedCurrencyEntity

@Dao
interface CombinedCurrencyDao {

//  @Query(
//    """
//        SELECT id, name, symbol, NULL AS code
//        FROM crypto_currency
//        WHERE name LIKE :query || '%'
//        OR name LIKE '% ' || :query || '%'
//        OR symbol LIKE :query || '%'
//
//        UNION ALL
//
//        SELECT id, name, symbol, code
//        FROM fiat_currency
//        WHERE name LIKE :query || '%'
//        OR name LIKE '% ' || :query || '%'
//        OR symbol LIKE :query || '%'
//        """
//  )

  // can show FIAT!!
//@Query(
//  """
//        SELECT cc.id, cc.name, cc.symbol, NULL AS code
//        FROM crypto_currency cc
//        INNER JOIN fiat_currency fc ON cc.symbol = fc.symbol
//        WHERE cc.name LIKE :query || '%'
//        OR cc.name LIKE '% ' || :query || '%'
//        OR cc.symbol LIKE :query || '%'
//
//        UNION ALL
//
//        SELECT fc.id, fc.name, fc.symbol, fc.code
//        FROM fiat_currency fc
//        WHERE fc.name LIKE :query || '%'
//        OR fc.name LIKE '% ' || :query || '%'
//        OR fc.symbol LIKE :query || '%'
//        """
//)

  // It works!!!!!!!!!!!!
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
