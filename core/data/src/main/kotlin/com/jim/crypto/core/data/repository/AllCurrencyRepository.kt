package com.jim.crypto.core.data.repository

import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class AllCurrencyRepository(
  private val cryptoCurrencyRepository: CryptoCurrencyRepository,
  private val fiatCurrencyRepository: FiatCurrencyRepository
) {

  fun getItems(): Flow<List<CurrencyInfo>> =
    combine(
      cryptoCurrencyRepository.getItems(),
      fiatCurrencyRepository.getItems()
    ) { cryptoCurrencies, fiatCurrencies ->
      cryptoCurrencies + fiatCurrencies
    }

  fun getItems(query: String): Flow<List<CurrencyInfo>> =
    combine(
      cryptoCurrencyRepository.getItems(query),
      fiatCurrencyRepository.getItems(query)
    ) { cryptoCurrencies, fiatCurrencies ->
      cryptoCurrencies + fiatCurrencies
    }
}