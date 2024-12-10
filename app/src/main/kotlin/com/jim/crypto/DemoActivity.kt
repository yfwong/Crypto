package com.jim.crypto

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
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
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jim.crypto.core.model.data.CurrencyInfo
import com.jim.crypto.core.ui.currencylist.CurrencyListFragment
import com.jim.crypto.core.ui.currencylist.CurrencyListScreen
import com.jim.crypto.core.ui.currencylist.CurrencyListViewModel
import com.jim.crypto.core.ui.theme.Dimens
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.ext.android.viewModel

class DemoActivity : AppCompatActivity() {

  private val demoViewModel: DemoViewModel by viewModel()
  private val currencyListViewModel: CurrencyListViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
      DemoNavHost(
        supportFragmentManager,
        navController,
        demoViewModel,
        currencyListViewModel
      )
    }
  }
}

@Composable
fun DemoNavHost(
  fragmentManager: FragmentManager,
  navController: NavHostController,
  demoViewModel: DemoViewModel,
  currencyListViewModel: CurrencyListViewModel
) {
  NavHost(navController = navController, startDestination = "demo") {
    // Demo screen with 5 buttons
    composable("demo") {
      DemoScreen(
        viewModel = demoViewModel,
        onNavigateToCurrencyList = { currencies ->
          // attach CurrencyListFragment (which is a wrapper of CurrencyListScreen)
          fragmentManager.commit {
            setReorderingAllowed(true)
            replace(android.R.id.content, CurrencyListFragment.newInstance(currencies))
            addToBackStack(null)
          }

          // or... attach composable directly
//          val jsonString = Json.encodeToString(
//            ListSerializer(CurrencyInfo.serializer()),
//            currencies
//          )
//          navController.navigate("CurrencyList/${jsonString}")
        }
      )
    }

    // Currency list screen for display and search currencyInfo items
    composable(
      route = "CurrencyList/{currencies}",
      arguments = listOf(navArgument("currencies") { type = NavType.StringType })
    ) { backStackEntry ->
      val json = backStackEntry.arguments?.getString("currencies")
      val currencies = json?.let {
        Json.decodeFromString<List<CurrencyInfo>>(it)
      } ?: emptyList()
      CurrencyListScreen(currencies = currencies, currencyListViewModel)
    }
  }
}

@Composable
fun DemoScreen(
  viewModel: DemoViewModel,
  onNavigateToCurrencyList: (currencies: List<CurrencyInfo>) -> Unit
) {
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
        onNavigateToCurrencyList(cryptoCurrencies)
      }) {
        Text(stringResource(R.string.open_crypto_currency_list))
      }
      Button(onClick = { onNavigateToCurrencyList(fiatCurrencies) }) {
        Text(stringResource(R.string.open_fiat_currency_list))
      }
      Button(onClick = { onNavigateToCurrencyList(bothCurrencies) }) {
        Text(stringResource(R.string.open_both_currency_list))
      }
    }
  }
}