package com.jim.crypto.di

import com.jim.crypto.DemoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
  viewModel { DemoViewModel(get(), get(), get()) }
}