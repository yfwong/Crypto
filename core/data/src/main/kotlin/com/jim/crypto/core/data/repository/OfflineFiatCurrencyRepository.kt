package com.jim.crypto.core.data.repository

import com.jim.crypto.core.data.model.asExternalModel
import com.jim.crypto.core.data.model.asFiatEntity
import com.jim.crypto.core.database.dao.FiatCurrencyDao
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineFiatCurrencyRepository(
  private val dao: FiatCurrencyDao
) : FiatCurrencyRepository {
  override fun getFiatCurrencies(query: String): Flow<List<CurrencyInfo>> =
    dao.getFiatCurrencies("%$query%").map { flow -> flow.map { it.asExternalModel() } }

  override suspend fun addFiatCurrencies(currencies: List<CurrencyInfo>) =
    dao.insertFiatCurrencies(currencies.map { it.asFiatEntity() })

  override suspend fun deleteFiatCurrencies() =
    dao.deleteFiatCurrencies()
}
