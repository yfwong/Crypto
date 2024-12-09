package com.jim.crypto.core.ui.di

import com.jim.crypto.core.ui.currencylist.AllCurrencyListViewModel
import com.jim.crypto.core.ui.currencylist.CryptoCurrencyListViewModel
import com.jim.crypto.core.ui.currencylist.FiatCurrencyListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
  viewModel { CryptoCurrencyListViewModel(get()) }
  viewModel { FiatCurrencyListViewModel(get()) }
  viewModel { AllCurrencyListViewModel(get()) }
}