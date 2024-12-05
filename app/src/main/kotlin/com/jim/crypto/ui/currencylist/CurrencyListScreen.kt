package com.jim.crypto.ui.currencylist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.jim.crypto.ui.component.SearchBar
import com.jim.crypto.ui.component.SearchButton
import com.jim.crypto.ui.theme.AppSpacing

@Composable
fun CurrencyListScreen(viewModel: CurrencyListViewModel) {
  var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
  val currencies by viewModel.currencies.collectAsState()
  val isSearching by viewModel.isSearching.collectAsState()

  LaunchedEffect(searchQuery) {
    if (isSearching) {
      viewModel.searchCurrencies(searchQuery.text)
    } else {
      searchQuery = TextFieldValue("")
      viewModel.getAllCurrencies()
    }
  }

  Column(modifier = Modifier.padding(AppSpacing.Medium)) {
    if (isSearching) {
      SearchBar(
        searchQuery = searchQuery,
        onQueryChange = { searchQuery = it },
        onNavigateBack = {
          viewModel.stopSearchCurrencies()
        })
    } else {
      Box(modifier = Modifier.fillMaxWidth()) {
        SearchButton(onClick = { viewModel.startSearchingCurrencies() })
      }
    }
    CurrencyList(currencies = currencies, showDivider = isSearching)
    if (isSearching && currencies.isEmpty()) {
      EmptyState()
    }
  }
}