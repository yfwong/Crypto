package com.jim.crypto.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.jim.crypto.core.data.model.asExternalModel
import com.jim.crypto.core.data.model.asFiatEntity
import com.jim.crypto.core.database.dao.FiatCurrencyDao
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FiatCurrencyRepository(
  private val dao: FiatCurrencyDao
) : CurrencyRepository {

  override fun getPagedItems(query: String): Flow<PagingData<CurrencyInfo>> =
    Pager(config = PagingConfig(pageSize = 100),
      pagingSourceFactory = { dao.getPagedItems(query) }
    ).flow.map { pagingData ->
      pagingData.map { entity ->
        entity.asExternalModel()
      }
    }

  override suspend fun inertItems(currencies: List<CurrencyInfo>) =
    dao.insertItems(currencies.map { it.asFiatEntity() })

  override suspend fun deleteItems() =
    dao.deleteItems()
}
