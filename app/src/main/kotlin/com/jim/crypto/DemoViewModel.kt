package com.jim.crypto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jim.crypto.core.data.repository.CurrencyRepository
import com.jim.crypto.core.data.repository.DemoJsonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DemoViewModel(
  private val cryptoRepo: CurrencyRepository,
  private val fiatRepo: CurrencyRepository,
  private val demoJsonRepo: DemoJsonRepository,
) : ViewModel() {

  private val _snackbarMessage = MutableStateFlow<String?>(null)
  val snackbarMessage: StateFlow<String?> = _snackbarMessage

  fun onDataDelete(message: String) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        cryptoRepo.deleteItems()
        fiatRepo.deleteItems()
      }
      _snackbarMessage.value = message
    }
  }

  fun onDataInsert(message: String) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        cryptoRepo.inertItems(demoJsonRepo.getCryptoDataFromJson())
        fiatRepo.inertItems(demoJsonRepo.getFiatDataFromJson())
      }
      _snackbarMessage.value = message
    }
  }
}