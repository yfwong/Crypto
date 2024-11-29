package com.jim.crypto

import android.app.Application
import com.jim.crypto.core.data.di.DataModule
import com.jim.crypto.core.database.di.DaoModule
import com.jim.crypto.core.database.di.DatabaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import org.koin.ksp.generated.*

class MainApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@MainApplication)
      modules(
        DaoModule().module,
        DatabaseModule().module,
        DataModule().module,
      )
    }
  }
}