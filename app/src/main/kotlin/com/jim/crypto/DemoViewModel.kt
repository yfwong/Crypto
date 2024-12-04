package com.jim.crypto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jim.crypto.core.data.repository.CurrencyRepository
import com.jim.crypto.core.data.repository.DemoJsonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DemoViewModel(
  private val cryptoRepo: CurrencyRepository,
  private val fiatRepo: CurrencyRepository,
  private val demoJsonRepo: DemoJsonRepository,
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

  fun insertData(onComplete: () -> Unit) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        cryptoRepo.addCurrencies(demoJsonRepo.getCryptoDataFromJson())
        fiatRepo.addCurrencies(demoJsonRepo.getFiatDataFromJson())
      }
      onComplete()
    }
  }
}