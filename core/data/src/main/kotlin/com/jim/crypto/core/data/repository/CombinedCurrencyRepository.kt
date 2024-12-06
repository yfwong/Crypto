package com.jim.crypto.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.jim.crypto.core.data.model.asExternalModel
import com.jim.crypto.core.database.dao.CombinedCurrencyDao
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CombinedCurrencyRepository(
  private val dao: CombinedCurrencyDao
) : CurrencyRepository {
  override fun getPagedItems(query: String): Flow<PagingData<CurrencyInfo>> {
    return Pager(config = PagingConfig(pageSize = 100),
      pagingSourceFactory = { dao.getPagedItems(query) }
    ).flow.map { pagingData ->
      pagingData.map {
        it.asExternalModel()
      }
    }
  }

  override suspend fun inertItems(currencies: List<CurrencyInfo>) {
    throw Exception("insert feature not supported")
  }

  override suspend fun deleteItems() {
    throw Exception("delete feature not supported")
  }
}
