package com.jim.crypto.ui.currencylist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import com.jim.crypto.core.model.data.CurrencyInfo

@Composable
fun CurrencyList(currencies: List<CurrencyInfo>, showDivider: Boolean) {
  LazyColumn {
    items(currencies) { currency ->
      CurrencyItem(currency)
      if (showDivider) {
        HorizontalDivider()
      }
    }
  }
}