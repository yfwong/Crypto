package com.jim.crypto.core.data.repository

import com.jim.crypto.core.data.model.asCryptoEntity
import com.jim.crypto.core.data.model.asExternalModel
import com.jim.crypto.core.database.dao.CryptoCurrencyDao
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineCryptoCurrencyRepository(
  private val dao: CryptoCurrencyDao
) : CryptoCurrencyRepository {

  override fun getCryptoCurrencies(query: String): Flow<List<CurrencyInfo>> =
    dao.getCryptoCurrencies("%$query%").map { flow -> flow.map { it.asExternalModel() } }

  override suspend fun addCryptoCurrencies(currencies: List<CurrencyInfo>) =
    dao.insertCryptoCurrencies(currencies.map { it.asCryptoEntity() })

  override suspend fun deleteCryptoCurrencies() =
    dao.deleteCryptoCurrencies()
}
