package com.jim.crypto.ui.currencylist

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jim.crypto.core.model.data.CurrencyInfo

@Composable
fun CurrencyList(currencies: List<CurrencyInfo>, showDivider: Boolean) {
  LazyColumn(modifier = Modifier.background(Color.White)) {
    items(currencies) { currency ->
      CurrencyItem(currency)
      if (showDivider)
        HorizontalDivider()
      else
        HorizontalDivider(color = Color.Transparent)
    }
  }
}