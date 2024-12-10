package com.jim.crypto.core.ui.di

import com.jim.crypto.core.ui.currencylist.CurrencyListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
  viewModelOf(::CurrencyListViewModel)
}