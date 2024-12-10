package com.jim.crypto.core.data.di

import com.jim.crypto.core.data.DispatcherType
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatcherModule = module {
  single(named(DispatcherType.IO)) { Dispatchers.IO }
  single(named(DispatcherType.Default)) { Dispatchers.Default }
}