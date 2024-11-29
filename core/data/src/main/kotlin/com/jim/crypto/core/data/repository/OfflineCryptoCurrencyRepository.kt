package com.jim.crypto.core.data.repository

import com.jim.crypto.core.data.model.asCryptoEntity
import com.jim.crypto.core.data.model.asExternalModel
import com.jim.crypto.core.database.dao.CryptoCurrencyDao
import com.jim.crypto.core.model.data.CurrencyInfo

class OfflineCryptoCurrencyRepository(
  private val dao: CryptoCurrencyDao
) : CryptoCurrencyRepository {

  override suspend fun getCryptoCurrencies(query: String?): List<CurrencyInfo> =
    dao.getCryptoCurrencies().map { it.asExternalModel() }

  override suspend fun addCryptoCurrencies(currencies: List<CurrencyInfo>) =
    dao.upsertCryptoCurrencies(currencies.map { it.asCryptoEntity() })

  override suspend fun deleteCryptoCurrencies() =
    dao.deleteCryptoCurrencies()
}
