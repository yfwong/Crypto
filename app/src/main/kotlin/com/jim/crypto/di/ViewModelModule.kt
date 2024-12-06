package com.jim.crypto.di

import com.jim.crypto.DemoViewModel
import com.jim.crypto.core.data.di.RepositoryQualifier.*
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
  viewModel { DemoViewModel(get(CryptoRepo), get(FiatRepo), get()) }
}