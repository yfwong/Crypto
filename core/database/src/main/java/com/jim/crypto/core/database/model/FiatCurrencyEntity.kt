package com.jim.crypto.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fiat_currency")
data class FiatCurrencyEntity(
  @PrimaryKey val id: String,
  val name: String,
  @ColumnInfo("symbol")
  val symbol: String,
  @ColumnInfo("code")
  val code: String
)
