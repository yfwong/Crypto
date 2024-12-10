package com.jim.crypto

import android.app.Application
import com.jim.crypto.core.data.di.dataModule
import com.jim.crypto.core.data.di.dispatcherModule
import com.jim.crypto.core.database.di.databaseModule
import com.jim.crypto.core.domain.di.domainModule
import com.jim.crypto.core.ui.di.uiModule
import com.jim.crypto.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@MainApplication)
      modules(
        appModule,
        uiModule,
        domainModule,
        dataModule,
        databaseModule,
        dispatcherModule
      )
    }
  }
}