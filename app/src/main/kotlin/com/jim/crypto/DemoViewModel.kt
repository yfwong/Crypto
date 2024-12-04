package com.jim.crypto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jim.crypto.core.data.repository.CurrencyRepository
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DemoViewModel(
  private val cryptoRepo: CurrencyRepository,
  private val fiatRepo: CurrencyRepository
) : ViewModel() {

  fun deleteData(onComplete: () -> Unit) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        cryptoRepo.deleteCurrencies()
        fiatRepo.deleteCurrencies()
      }
      onComplete()
    }
  }

  fun insertCryptoData(currencies: List<CurrencyInfo>, onComplete: () -> Unit) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        cryptoRepo.addCurrencies(currencies)
      }
      onComplete()
    }
  }

  fun insertFiatData(currencies: List<CurrencyInfo>, onComplete: () -> Unit) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        fiatRepo.addCurrencies(currencies)
      }
      onComplete()
    }
  }
}