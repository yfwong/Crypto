package com.jim.crypto.core.database.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class CombinedCurrencyEntity(
  @PrimaryKey val id: String,
  @ColumnInfo("name")
  val name: String,
  @ColumnInfo("symbol")
  val symbol: String,
  @ColumnInfo("code")
  val code: String? = null
)
