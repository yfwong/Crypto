package com.jim.crypto.di

import com.jim.crypto.DemoViewModel
import com.jim.crypto.data.repository.DemoJsonRepository
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
  singleOf(::DemoJsonRepository)
  viewModelOf(::DemoViewModel)
}