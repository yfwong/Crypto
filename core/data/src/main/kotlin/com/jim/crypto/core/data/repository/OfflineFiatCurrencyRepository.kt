package com.jim.crypto.core.data.repository

import com.jim.crypto.core.data.model.asEntity
import com.jim.crypto.core.data.model.asExternalModel
import com.jim.crypto.core.database.dao.FiatCurrencyDao
import com.jim.crypto.core.model.data.FiatCurrency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineFiatCurrencyRepository(
  private val dao: FiatCurrencyDao
) : FiatCurrencyRepository {

  override fun getFiatCurrencies(query: String?): Flow<List<FiatCurrency>> =
    dao.getFiatCurrencies().map { it.asExternalModel() }

  override suspend fun addFiatCurrencies(currencies: List<FiatCurrency>) =
    dao.upsertFiatCurrencies(currencies.asEntity())

  override suspend fun deleteFiatCurrencies() =
    dao.deleteFiatCurrencies()
}
