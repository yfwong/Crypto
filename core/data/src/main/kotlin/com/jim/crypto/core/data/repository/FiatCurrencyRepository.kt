package com.jim.crypto.core.data.repository

import com.jim.crypto.core.data.model.asExternalModel
import com.jim.crypto.core.data.model.asFiatEntity
import com.jim.crypto.core.database.dao.FiatCurrencyDao
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface FiatCurrencyRepository {
  fun getItems(): Flow<List<CurrencyInfo>>
  fun getItems(query: String): Flow<List<CurrencyInfo>>
  suspend fun getItemsSync(): List<CurrencyInfo>
  suspend fun inertItems(currencies: List<CurrencyInfo>)
  suspend fun deleteItems()
}

class FiatCurrencyRepositoryImpl(
  private val dao: FiatCurrencyDao,
  private val dispatcher: CoroutineDispatcher
) : FiatCurrencyRepository {

  override fun getItems(): Flow<List<CurrencyInfo>> =
    dao.getItems().map { flow -> flow.map { it.asExternalModel() } }

  override fun getItems(query: String): Flow<List<CurrencyInfo>> =
    dao.getItems(query).map { flow -> flow.map { it.asExternalModel() } }

  override suspend fun getItemsSync(): List<CurrencyInfo> = withContext(dispatcher) {
    dao.getItemsSync().map { it.asExternalModel() }
  }

  override suspend fun inertItems(currencies: List<CurrencyInfo>) = withContext(dispatcher) {
    dao.insertItems(currencies.map { it.asFiatEntity() })
  }

  override suspend fun deleteItems() = withContext(dispatcher) {
    dao.deleteItems()
  }
}
