package com.jim.crypto

import android.app.Application
import com.jim.crypto.core.data.di.dataModule
import com.jim.crypto.core.database.di.databaseModule
import com.jim.crypto.core.domain.di.useCaseModule
import com.jim.crypto.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@MainApplication)
      modules(
        viewModelModule,
        useCaseModule,
        dataModule,
        databaseModule
      )
    }
  }
}