package com.jim.crypto.core.domain

import com.jim.crypto.core.data.repository.InMemoryCurrencyRepository
import com.jim.crypto.core.model.data.CurrencyInfo

class SetCurrencyListUseCase(
  private val repository: InMemoryCurrencyRepository,
) {
  suspend operator fun invoke(currencies: List<CurrencyInfo>) {
    repository.setItems(currencies)
  }
}