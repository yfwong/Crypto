package com.jim.crypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jim.crypto.ui.DemoScreen
import com.jim.crypto.ui.currencylist.CurrencyListScreen
import com.jim.crypto.ui.currencylist.CurrencyListViewModel
import com.jim.crypto.ui.theme.CryptoTheme
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
  private val currencyListViewModel: CurrencyListViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CryptoTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "demo") {
          composable("demo") { DemoScreen(navController, demoViewModel) }
          composable("currencyList") { CurrencyListScreen(navController, currencyListViewModel) }
        }
      }
    }
  }
}
