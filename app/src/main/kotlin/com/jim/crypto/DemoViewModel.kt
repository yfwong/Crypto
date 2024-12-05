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

  // Function to update the snackbar message
  fun showSnackbar(message: String) {
    _snackbarMessage.value = message
  }

  // Function to reset the snackbar message after it's been shown
  fun onSnackbarShown() {
    _snackbarMessage.value = null
  }
}