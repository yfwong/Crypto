package com.jim.crypto.ui.currencylist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jim.crypto.core.domain.usecase.GetCurrencyListUseCase
import com.jim.crypto.core.domain.usecase.SearchCurrencyListUseCase
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CurrencyListViewModel(
  private val getCurrencyListUseCase: GetCurrencyListUseCase,
  private val searchCurrencyListUseCase: SearchCurrencyListUseCase
) : ViewModel() {

//  private val _currencies = MutableStateFlow<List<CurrencyInfo>>(emptyList())
//  val currencies: StateFlow<List<CurrencyInfo>> = _currencies

  private val _isShowSearchInput = MutableStateFlow(false)
  val isShowSearchInput: StateFlow<Boolean> = _isShowSearchInput

  //  var query by mutableStateOf("")
//    private set
  private val _searchQuery = MutableStateFlow("")
  val searchQuery: StateFlow<String> = _searchQuery

  @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
  val pagedItems: Flow<PagingData<CurrencyInfo>> = _searchQuery
    .debounce(300) // To handle quick typing
    .distinctUntilChanged() // Avoid unnecessary updates
    .flatMapLatest { query -> searchCurrencyListUseCase(query) }
    .cachedIn(viewModelScope)
//    .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

  fun onQueryChange(newQuery: String) {
    _searchQuery.value = newQuery
  }

//  fun getAllCurrencies() {
//    viewModelScope.launch(Dispatchers.IO) {
//      getCurrencyListUseCase().collect {
//        _currencies.value = it
//      }
//    }
//  }
//
//  fun searchCurrencies(query: String) {
//    viewModelScope.launch(Dispatchers.IO) {
//      searchCurrencyListUseCase(query).collect {
//        _currencies.value = it
//      }
//    }
//  }

  fun showSearchInput(show: Boolean) {
    _isShowSearchInput.value = show
  }
}