package com.jim.crypto.core.data.repository

import android.util.Log
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

  // Only output Crypto
//  override fun getPagedItems(query: String): Flow<PagingData<CurrencyInfo>> {
//    val cryptoPagingFlow = Pager(
//      config = PagingConfig(pageSize = 20, enablePlaceholders = false),
//      pagingSourceFactory = { cryptoCurrencyDao.getPagedItems(query) }
//    ).flow.map { pagingData ->
//      pagingData.map {
//        Log.d("JIMWONG", "repo(crypto): $it")
//        it.asExternalModel()
//      }
//    }
//
//    val fiatPagingFlow = Pager(
//      config = PagingConfig(pageSize = 20, enablePlaceholders = false),
//      pagingSourceFactory = { fiatCurrencyDao.getPagedItems(query) }
//    ).flow.map { pagingData ->
//      pagingData.map {
//        Log.d("JIMWONG", "repo(fiat): $it")
//        it.asExternalModel()
//      }
//    }
//
//    // Combine both PagingData streams
//    return merge(cryptoPagingFlow, fiatPagingFlow)
//  }

  override fun getPagedItems(query: String): Flow<PagingData<CurrencyInfo>> {
    Log.d("JIMWONG", "A!");
    return Pager(config = PagingConfig(pageSize = 100),
      pagingSourceFactory = { dao.getPagedItems(query) }
    ).flow.map { pagingData ->
      pagingData.map { entity ->
        Log.d("JIMWONG", ">> $entity");
        entity.asExternalModel()
      }
    }
  }

  override fun getCurrencies(): Flow<List<CurrencyInfo>> {
    TODO("Not yet implemented")
  }

  override fun searchCurrencies(query: String): Flow<List<CurrencyInfo>> {
    TODO("Not yet implemented")
  }

  override suspend fun addCurrencies(currencies: List<CurrencyInfo>) {
    TODO("Not yet implemented")
  }

  override suspend fun deleteCurrencies() {
    TODO("Not yet implemented")
  }
}
