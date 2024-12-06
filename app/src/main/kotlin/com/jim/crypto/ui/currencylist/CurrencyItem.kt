package com.jim.crypto.ui.currencylist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.jim.crypto.core.model.data.CurrencyInfo
import com.jim.crypto.ui.component.LetterBadge
import com.jim.crypto.ui.theme.Dimen

@Composable
fun CurrencyItem(currency: CurrencyInfo) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(Dimen.Medium),
    horizontalArrangement = Arrangement.Start,
    verticalAlignment = Alignment.CenterVertically
  ) {
    // Display Badge with initial
    LetterBadge(currency.name.first().toString())
    Spacer(modifier = Modifier.width(Dimen.Medium))
    Text(text = currency.name, style = typography.bodyLarge)
    if (currency.code == null) {
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
  CurrencyItem(CurrencyInfo("BTC", "Bitcoin", "BTC", null))
}

@Preview(showBackground = true)
@Composable
fun PreviewFiatCurrencyItem() {
  CurrencyItem(CurrencyInfo("HKD", "Hong Kong Dollar", "$", "HKD"))
}