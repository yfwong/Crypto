package com.jim.crypto.core.database.di

import android.content.Context
import androidx.room.Room
import com.jim.crypto.core.database.AppDatabase
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class DatabaseModule {

  @Single
  fun provideDatabase(context: Context): AppDatabase = Room.databaseBuilder(
    context,
    AppDatabase::class.java,
    "app_database"
  ).build()
}