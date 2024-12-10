package com.jim.crypto.core.ui.di

import com.jim.crypto.core.ui.currencylist.AllCurrencyListViewModel
import com.jim.crypto.core.ui.currencylist.CryptoCurrencyListViewModel
import com.jim.crypto.core.ui.currencylist.FiatCurrencyListViewModel
import com.jim.crypto.core.ui.currencylist.InMemoryCurrencyListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
  viewModelOf(::CryptoCurrencyListViewModel)
  viewModelOf(::FiatCurrencyListViewModel)
  viewModelOf(::AllCurrencyListViewModel)
  viewModelOf(::InMemoryCurrencyListViewModel)
}