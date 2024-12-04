package com.jim.crypto

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jim.crypto.di.ViewModelModifier
import com.jim.crypto.ui.currencylist.CurrencyListScreen
import com.jim.crypto.ui.theme.AppSpacing
import com.jim.crypto.ui.theme.CryptoTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * The DemoActivity should provide two datasets, Currency List A and Currency List B,
 * which contain CurrencyInfo objects to be queried from the local database.
 *
 * The DemoActivity should also include five buttons for demonstrating various
 * functionalities:
 * ○ The first button is responsible for clearing the data in the local database.
 * ○ The second button is used to insert the data into the local database.
 * ○ The third button changes the CurrencyListFragment to use Currency List A -
 * Crypto.
 * ○ The fourth button changes the CurrencyListFragment to use Currency List B -
 * Fiat.
 * ○ The fifth button displays all CurrencyInfo objects that can be purchased from
 * Currency List A and B.
 */
class DemoActivity : ComponentActivity() {

  private val demoViewModel: DemoViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CryptoTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "demo") {
          composable("demo") { DemoScreen(navController, demoViewModel) }
          composable("CryptoCurrencyList") {
            CurrencyListScreen(navController, getViewModel(ViewModelModifier.Crypto))
          }
          composable("FiatCurrencyList") {
            CurrencyListScreen(navController, getViewModel(ViewModelModifier.Fiat))
          }
          composable("MixedCurrencyList") {
            CurrencyListScreen(navController, getViewModel(ViewModelModifier.Mixed))
          }
        }
      }
    }
  }
}

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
      viewModel.insertData { showPopup = dataInsertedText }
    }) {
      Text(stringResource(R.string.insert_data))
    }
    Spacer(modifier = Modifier.height(AppSpacing.Small))
    Button(onClick = { navController.navigate("CryptoCurrencyList") }) {
      Text(stringResource(R.string.open_crypto_currency_list))
    }
    Spacer(modifier = Modifier.height(AppSpacing.Small))
    Button(onClick = { navController.navigate("FiatCurrencyList") }) {
      Text(stringResource(R.string.open_fiat_currency_list))
    }
    Spacer(modifier = Modifier.height(AppSpacing.Small))
    Button(onClick = { navController.navigate("MixedCurrencyList") }) {
      Text(stringResource(R.string.open_mixed_currency_list))
    }
  }
}