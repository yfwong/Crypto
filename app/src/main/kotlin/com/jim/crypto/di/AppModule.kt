package com.jim.crypto.di

import com.jim.crypto.ui.currencylist.CurrencyListViewModel
import com.jim.crypto.DemoViewModel
import com.jim.crypto.core.data.di.CryptoQualifier
import com.jim.crypto.core.data.di.FiatQualifier
import org.koin.core.module.dsl.viewModel
//import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
  viewModel { DemoViewModel(get(CryptoQualifier), get(FiatQualifier)) }
  viewModel { CurrencyListViewModel(get(CryptoQualifier)) }
  viewModel { CurrencyListViewModel(get(FiatQualifier)) }
}