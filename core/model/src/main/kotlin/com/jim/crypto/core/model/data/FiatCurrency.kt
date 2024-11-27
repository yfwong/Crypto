package com.jim.crypto.core.model.data

/**
 * External data layer representation of a Fiat currency
 **/
data class FiatCurrency(
  val id: String,
  val name: String,
  val symbol: String,
  val code: String
)
