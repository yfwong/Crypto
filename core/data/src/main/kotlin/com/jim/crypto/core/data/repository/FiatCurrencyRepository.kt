package com.jim.crypto.core.data.repository

import com.jim.crypto.core.data.model.asExternalModel
import com.jim.crypto.core.data.model.asFiatEntity
import com.jim.crypto.core.database.dao.FiatCurrencyDao
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FiatCurrencyRepository(
  private val dao: FiatCurrencyDao
) : CurrencyRepository {

  override fun getItems(): Flow<List<CurrencyInfo>> =
    dao.getItems().map { flow -> flow.map { it.asExternalModel() } }

  override fun getItems(query: String): Flow<List<CurrencyInfo>> =
    dao.getItems(query).map { flow -> flow.map { it.asExternalModel() } }

  override suspend fun getItemsSync(): List<CurrencyInfo> =
    dao.getItemsSync().map { it.asExternalModel() }

  override suspend fun inertItems(currencies: List<CurrencyInfo>) =
    dao.insertItems(currencies.map { it.asFiatEntity() })

  override suspend fun deleteItems() =
    dao.deleteItems()
}
