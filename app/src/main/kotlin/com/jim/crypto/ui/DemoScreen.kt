package com.jim.crypto.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.jim.crypto.DemoViewModel
import com.jim.crypto.R
import com.jim.crypto.core.model.data.CurrencyInfo
import com.jim.crypto.ui.theme.AppSpacing

@Composable
fun DemoScreen(navController: NavController, viewModel: DemoViewModel) {
  val dataClearedText = stringResource(R.string.data_cleared)
  val dataInsertedText = stringResource(R.string.data_inserted)
  var showPopup by remember { mutableStateOf<String?>(null) }

  if (showPopup != null) {
    Toast.makeText(LocalContext.current, showPopup, Toast.LENGTH_SHORT).show()
    showPopup = null
  }
  Column(
    modifier = Modifier
      .padding(AppSpacing.Medium)
      .fillMaxWidth()
      .fillMaxHeight(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Button(onClick = {
      viewModel.deleteData {
        showPopup = dataClearedText
      }
    }) {
      Text(stringResource(R.string.clear_database))
    }
    Spacer(modifier = Modifier.height(AppSpacing.Small))
    Button(onClick = {

      viewModel.insertCryptoData(
        listOf(
          CurrencyInfo("BTC", "Bitcoin", "BTC"),
          CurrencyInfo("CRO", "Cronos", "CRO")
        )
      ) {
        showPopup = dataInsertedText
      }

      viewModel.insertFiatData(
        listOf(
          CurrencyInfo("HKD", "Hong Kong Dollars", "$", "HKD"),
          CurrencyInfo("USD", "US Dollars", "$$", "USD")
        )
      ) {
        showPopup = dataInsertedText
      }
    }) {
      Text(stringResource(R.string.insert_data))
    }
    Spacer(modifier = Modifier.height(AppSpacing.Small))
    Button(onClick = { navController.navigate("currencyList") }) {
      Text(stringResource(R.string.open_currency_list))
    }
  }
}