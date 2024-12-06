package com.jim.crypto.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
  tableName = "fiat_currency",
  indices = [Index(value = ["name"]), Index(value = ["symbol"])]
)
data class FiatCurrencyEntity(
  @PrimaryKey val id: String,
  @ColumnInfo("name")
  val name: String,
  @ColumnInfo("symbol")
  val symbol: String,
  @ColumnInfo("code")
  val code: String? = null
)
