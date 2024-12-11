@file:OptIn(ExperimentalMaterial3Api::class)

package com.jim.crypto.core.ui.currencylist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jim.crypto.core.model.data.CurrencyInfo
import com.jim.crypto.core.ui.component.SearchTopBar
import com.jim.crypto.core.ui.theme.Colors

@Composable
fun CurrencyListScreen(
  currencies: List<CurrencyInfo>,
  viewModel: CurrencyListViewModel
) {
  val isShowSearchInput by viewModel.isShowSearchInput.collectAsState()
  val query by viewModel.searchQuery.collectAsState()
  val items by viewModel.items.collectAsState(emptyList())

  LaunchedEffect(currencies) {
    viewModel.initializeCurrencyList(currencies)
  }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      if (isShowSearchInput) {
        SearchTopBar(
          query = query,
          onNavigateBack = viewModel::onNavigateBack,
          onQueryChange = viewModel::onQueryChange,
          onQueryClear = viewModel::onQueryClear
        )
      } else {
        DefaultSearchBar(viewModel::onSearchClick)
      }
    }) { padding ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .background(Colors.CurrencyListBg)
        .padding(padding)
    ) {
      items(
        count = items.size,
        key = { index -> items[index].id }
      ) { index ->
        items[index].let { item ->
          CurrencyItem(item)
          HorizontalDivider(color = if (isShowSearchInput) Color.Gray else Color.Transparent)
        }
      }
      if (isShowSearchInput && items.isEmpty())
        item {
          EmptySearchResultView()
        }
    }
  }
}

@Composable
fun DefaultSearchBar(onSearchClick: () -> Unit) {
  TopAppBar(
    title = { },
    actions = {
      IconButton(onClick = onSearchClick) {
        Icon(Icons.Default.Search, contentDescription = "Search")
      }
    }
  )
}