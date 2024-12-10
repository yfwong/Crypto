package com.jim.crypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jim.crypto.core.model.data.CurrencyInfo
import com.jim.crypto.core.ui.currencylist.InMemoryCurrencyListScreen
import com.jim.crypto.core.ui.theme.Dimens
import com.jim.crypto.ui.theme.CryptoTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DemoActivity : ComponentActivity() {

  private val demoViewModel: DemoViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CryptoTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "demo") {
          composable("demo") { DemoScreen(navController, demoViewModel) }
          composable(
            route = "CurrencyList/{currencies}",
            arguments = listOf(navArgument("currencies") { type = NavType.StringType })
          ) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("currencies")
            val currencies = json?.let {
              Json.decodeFromString<List<CurrencyInfo>>(it)
            } ?: emptyList()
            InMemoryCurrencyListScreen(currencies = currencies, getViewModel())
          }
        }
      }
    }
  }
}

@Composable
fun DemoScreen(navController: NavController, viewModel: DemoViewModel) {
  val context = LocalContext.current
  val cryptoCurrencies by viewModel.getCryptoCurrenciesFromDb().collectAsState(emptyList())
  val fiatCurrencies by viewModel.getFiatCurrenciesFromDb().collectAsState(emptyList())
  val bothCurrencies by viewModel.getBothCurrenciesFromDb().collectAsState(emptyList())
  val snackbarMessage by viewModel.snackbarMessage.collectAsState()
  val snackbarHostState = SnackbarHostState()

  LaunchedEffect(snackbarMessage) {
    snackbarMessage?.let {
      snackbarHostState.showSnackbar(it)
    }
  }

  Scaffold(
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
  ) { padding ->
    Column(
      modifier = Modifier
        .padding(padding)
        .fillMaxWidth()
        .fillMaxHeight(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(Dimens.Medium, Alignment.CenterVertically),
    ) {
      Button(onClick = {
        viewModel.onDataDelete(context.getString(R.string.data_cleared))
      }) {
        Text(stringResource(R.string.clear_database))
      }
      Button(onClick = {
        viewModel.onDataInsert(context.getString(R.string.data_inserted))
      }) {
        Text(stringResource(R.string.insert_data))
      }
      Button(onClick = {
        navController.navigate("CurrencyList/${Json.encodeToString(cryptoCurrencies)}")
      }) {
        Text(stringResource(R.string.open_crypto_currency_list))
      }
      Button(onClick = {
        navController.navigate("CurrencyList/${Json.encodeToString(fiatCurrencies)}")
      }) {
        Text(stringResource(R.string.open_fiat_currency_list))
      }
      Button(onClick = {
        navController.navigate("CurrencyList/${Json.encodeToString(bothCurrencies)}")
      }) {
        Text(stringResource(R.string.open_both_currency_list))
      }
    }
  }
}