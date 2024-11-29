package com.jim.crypto.core.data.repository

import com.jim.crypto.core.data.model.asEntity
import com.jim.crypto.core.data.model.asExternalModel
import com.jim.crypto.core.database.dao.CryptoCurrencyDao
import com.jim.crypto.core.model.data.CryptoCurrency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineCryptoCurrencyRepository(
  private val dao: CryptoCurrencyDao
) : CryptoCurrencyRepository {

  override fun getCryptoCurrencies(query: String?): Flow<List<CryptoCurrency>> =
    dao.getCryptoCurrencies().map { it.asExternalModel() }

  override suspend fun addCryptoCurrencies(currencies: List<CryptoCurrency>) =
    dao.upsertCryptoCurrencies(currencies.asEntity())

  override suspend fun deleteCryptoCurrencies() =
    dao.deleteCryptoCurrencies()
}
