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
  @PrimaryKey override val id: String,
  @ColumnInfo("name")
  override val name: String,
  @ColumnInfo("symbol")
  override val symbol: String,
  @ColumnInfo("code")
  val code: String? = null
) : CurrencyEntity
