package com.jim.crypto.core.ui.currencylist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jim.crypto.core.model.data.CurrencyInfo
import com.jim.crypto.core.ui.theme.Colors

@Composable
fun CurrencyListScreen(
  currencies: List<CurrencyInfo>,
  viewModel: CurrencyListViewModel
) {
  val isShowSearchInput by viewModel.isShowSearchInput.collectAsState()
  val query by viewModel.searchQuery.collectAsState()
  val items by viewModel.pagedItems.collectAsState(emptyList())

  LaunchedEffect(currencies) {
    viewModel.initializeCurrencyList(currencies)
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Colors.CurrencyListBg)
  ) {
    SearchView(
      isShowSearchInput, query,
      onSearchClick = viewModel::onSearchClick,
      onQueryChange = viewModel::onQueryChange,
      onQueryClear = viewModel::onQueryClear,
      onNavigateBack = viewModel::onNavigateBack,
    )
    LazyColumn {
      items(
        count = items.size,
        key = { index -> items[index].id }
      ) { index ->
        items[index].let { item ->
          CurrencyItem(item)
          HorizontalDivider(color = if (isShowSearchInput) Color.Gray else Color.Transparent)
        }
      }
    }
    if (isShowSearchInput && items.isEmpty())
      EmptySearchResultView()
  }
}
