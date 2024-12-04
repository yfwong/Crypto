package com.jim.crypto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jim.crypto.core.data.model.isCrypto
import com.jim.crypto.core.data.repository.CryptoCurrencyRepository
import com.jim.crypto.core.data.repository.FiatCurrencyRepository
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DemoViewModel(
  private val cryptoRepo: CryptoCurrencyRepository,
  private val fiatRepo: FiatCurrencyRepository
) : ViewModel() {

  fun clearDatabase(onComplete: () -> Unit) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        cryptoRepo.deleteCryptoCurrencies()
        fiatRepo.deleteFiatCurrencies()
      }
      onComplete()
    }
  }

  fun insertData(currencies: List<CurrencyInfo>, onComplete: () -> Unit) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        if (currencies.firstOrNull()?.isCrypto() == true) {
          cryptoRepo.addCryptoCurrencies(currencies)
        } else {
          fiatRepo.addFiatCurrencies(currencies)
        }
      }
      onComplete()
    }
  }
}