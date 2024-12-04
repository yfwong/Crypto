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

  override fun getCurrencies(): Flow<List<CurrencyInfo>> =
    dao.getFiatCurrencies().map { flow -> flow.map { it.asExternalModel() } }

  override fun searchCurrencies(query: String): Flow<List<CurrencyInfo>> =
    dao.searchFiatCurrencies(query).map { flow -> flow.map { it.asExternalModel() } }

  override suspend fun addCurrencies(currencies: List<CurrencyInfo>) =
    dao.insertFiatCurrencies(currencies.map { it.asFiatEntity() })

  override suspend fun deleteCurrencies() =
    dao.deleteFiatCurrencies()
}
