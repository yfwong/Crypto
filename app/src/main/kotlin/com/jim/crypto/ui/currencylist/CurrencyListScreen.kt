package com.jim.crypto.ui.currencylist

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jim.crypto.core.model.data.CurrencyInfo
import com.jim.crypto.ui.theme.AppSpacing

@Composable
fun CurrencyListScreen(navController: NavController, viewModel: CurrencyListViewModel) {
  var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
  var currencies by remember { mutableStateOf(listOf<CurrencyInfo>()) }

  LaunchedEffect(searchQuery) {
    if (searchQuery.text.isEmpty()) {
      viewModel.getAllCurrencies().collect {
        currencies = it
      }
    } else {
      viewModel.searchCurrencies(searchQuery.text).collect {
        currencies = it
      }
    }
  }

  Column(modifier = Modifier.padding(AppSpacing.Medium)) {
    SearchBar(searchQuery = searchQuery, onQueryChange = {
      searchQuery = it
    })
    CurrencyList(currencies = currencies)
  }
}

@Composable
fun SearchBar(searchQuery: TextFieldValue, onQueryChange: (TextFieldValue) -> Unit) {
  BasicTextField(
    value = searchQuery,
    onValueChange = onQueryChange,
    modifier = Modifier.fillMaxWidth(),
    decorationBox = { innerTextField ->
      Row(modifier = Modifier.padding(AppSpacing.Small)) {
        Text("Search: ")
        innerTextField()
      }
    }
  )
}

@Composable
fun CurrencyList(currencies: List<CurrencyInfo>) {
  LazyColumn {
    items(currencies) { currency ->
      CurrencyItem(currency)
    }
  }
}

@Composable
fun CurrencyItem(currency: CurrencyInfo) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(AppSpacing.Small),
    horizontalArrangement = Arrangement.Start
  ) {
    // Display Badge with initial
    Text(
      text = currency.name.first().toString(),
      modifier = Modifier
        .padding(end = AppSpacing.Small)
        .border(1.dp, MaterialTheme.colorScheme.primary)
        .padding(AppSpacing.Small),
      style = MaterialTheme.typography.bodyLarge
    )
    Column {
      Text(text = currency.name, style = MaterialTheme.typography.bodyLarge)
      Text(text = currency.code, style = MaterialTheme.typography.bodySmall)
    }
  }
}