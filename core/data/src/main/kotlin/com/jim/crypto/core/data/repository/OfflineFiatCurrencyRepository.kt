package com.jim.crypto.core.data.repository

import com.jim.crypto.core.data.model.asExternalModel
import com.jim.crypto.core.data.model.asFiatEntity
import com.jim.crypto.core.database.dao.FiatCurrencyDao
import com.jim.crypto.core.model.data.CurrencyInfo

class OfflineFiatCurrencyRepository(
  private val dao: FiatCurrencyDao
) : FiatCurrencyRepository {
  override suspend fun getFiatCurrencies(query: String?): List<CurrencyInfo> =
    dao.getFiatCurrencies().map { it.asExternalModel() }

  override suspend fun addFiatCurrencies(currencies: List<CurrencyInfo>) =
    dao.upsertFiatCurrencies(currencies.map { it.asFiatEntity() })

  override suspend fun deleteFiatCurrencies() =
    dao.deleteFiatCurrencies()
}
