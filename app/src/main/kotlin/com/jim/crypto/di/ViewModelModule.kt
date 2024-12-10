package com.jim.crypto.di

import com.jim.crypto.DemoViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
  viewModelOf(::DemoViewModel)
}