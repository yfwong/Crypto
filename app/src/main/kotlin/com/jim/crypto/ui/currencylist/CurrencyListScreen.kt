package com.jim.crypto.ui.currencylist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.jim.crypto.core.model.data.CurrencyInfo
import com.jim.crypto.ui.component.LetterBadge
import com.jim.crypto.ui.component.SearchBar
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
    SearchBar(
      searchQuery = searchQuery,
      onQueryChange = { searchQuery = it },
      onNavigateBack = { navController.popBackStack() })
    HorizontalDivider()
    CurrencyList(currencies = currencies)
  }
}

@Composable
fun CurrencyList(currencies: List<CurrencyInfo>) {
  LazyColumn {
    items(currencies) { currency ->
      CurrencyItem(currency)
      HorizontalDivider()
    }
  }
}

@Composable
fun CurrencyItem(currency: CurrencyInfo) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(AppSpacing.Small),
    horizontalArrangement = Arrangement.Start,
    verticalAlignment = Alignment.CenterVertically
  ) {
    // Display Badge with initial
    LetterBadge(currency.name.first().toString())
    Spacer(modifier = Modifier.width(AppSpacing.Small))
    Text(text = currency.name, style = typography.bodyLarge)
    if (currency.code.isEmpty()) {
      Spacer(modifier = Modifier.weight(1f))
      Text(
        text = currency.symbol,
        style = typography.bodyLarge,
        color = Color.DarkGray
      )
      Icon(
        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
        contentDescription = "Back"
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewCryptoCurrencyItem() {
  CurrencyItem(CurrencyInfo("BTC", "Bitcoin", "BTC"))
}

@Preview(showBackground = true)
@Composable
fun PreviewFiatCurrencyItem() {
  CurrencyItem(CurrencyInfo("HKD", "Hong Kong Dollar", "$", "HKD"))
}