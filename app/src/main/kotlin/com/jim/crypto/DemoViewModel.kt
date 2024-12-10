package com.jim.crypto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jim.crypto.core.data.repository.CryptoCurrencyRepository
import com.jim.crypto.core.data.repository.FiatCurrencyRepository
import com.jim.crypto.core.model.data.CurrencyInfo
import com.jim.crypto.data.repository.DemoJsonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DemoViewModel(
  private val cryptoCurrencyRepository: CryptoCurrencyRepository,
  private val fiatCurrencyRepository: FiatCurrencyRepository,
  private val demoJsonRepository: DemoJsonRepository,
) : ViewModel() {

  private val _snackbarMessage = MutableStateFlow<String?>(null)
  val snackbarMessage: StateFlow<String?> = _snackbarMessage

  fun onDataDelete(message: String) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        cryptoCurrencyRepository.deleteItems()
        fiatCurrencyRepository.deleteItems()
      }
      _snackbarMessage.value = message
    }
  }

  fun onDataInsert(message: String) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        val cryptoData = demoJsonRepository.getCryptoDataFromJson()
        val fiatData = demoJsonRepository.getFiatDataFromJson()
        cryptoCurrencyRepository.inertItems(cryptoData)
        fiatCurrencyRepository.inertItems(fiatData)
      }
      _snackbarMessage.value = message
    }
  }

  fun getCryptoCurrenciesFromDb(): Flow<List<CurrencyInfo>> = cryptoCurrencyRepository.getItems()

  fun getFiatCurrenciesFromDb(): Flow<List<CurrencyInfo>> = fiatCurrencyRepository.getItems()

  fun getBothCurrenciesFromDb(): Flow<List<CurrencyInfo>> =
    cryptoCurrencyRepository.getItems().combine(fiatCurrencyRepository.getItems()) { crypto, fiat ->
      crypto + fiat
    }
}