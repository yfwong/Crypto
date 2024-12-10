package com.jim.crypto.core.data.repository

import com.jim.crypto.core.data.model.asExternalModel
import com.jim.crypto.core.data.model.asFiatEntity
import com.jim.crypto.core.database.dao.FiatCurrencyDao
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class FiatCurrencyRepository(
  private val dao: FiatCurrencyDao,
  private val dispatcher: CoroutineDispatcher
) {

  fun getItems(): Flow<List<CurrencyInfo>> =
    dao.getItems().map { flow -> flow.map { it.asExternalModel() } }

  fun getItems(query: String): Flow<List<CurrencyInfo>> =
    dao.getItems(query).map { flow -> flow.map { it.asExternalModel() } }

  suspend fun getItemsSync(): List<CurrencyInfo> = withContext(dispatcher) {
    dao.getItemsSync().map { it.asExternalModel() }
  }

  suspend fun inertItems(currencies: List<CurrencyInfo>) = withContext(dispatcher) {
    dao.insertItems(currencies.map { it.asFiatEntity() })
  }

  suspend fun deleteItems() = withContext(dispatcher) {
    dao.deleteItems()
  }
}
