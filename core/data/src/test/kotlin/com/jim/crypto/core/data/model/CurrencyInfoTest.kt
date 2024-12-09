package com.jim.crypto.core.data.model

import com.jim.crypto.core.database.model.CombinedCurrencyEntity
import com.jim.crypto.core.database.model.CryptoCurrencyEntity
import com.jim.crypto.core.database.model.FiatCurrencyEntity
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*

class CurrencyInfoTest {

  @Test
  fun `test CurrencyInfo to CryptoCurrencyEntity mapping`() = runTest {
    val currencyInfo = CurrencyInfo(
      id = "BTC",
      name = "Bitcoin",
      symbol = "BTC",
      code = null
    )

    val expected = CryptoCurrencyEntity(
      id = "BTC",
      name = "Bitcoin",
      symbol = "BTC"
    )

    val result = currencyInfo.asCryptoEntity()

    assertEquals(expected, result)
  }

  @Test
  fun `test CurrencyInfo to FiatCurrencyEntity mapping`() = runTest {
    val currencyInfo = CurrencyInfo(
      id = "USD",
      name = "US Dollar",
      symbol = "$",
      code = "USD"
    )

    val expected = FiatCurrencyEntity(
      id = "USD",
      name = "US Dollar",
      symbol = "$",
      code = "USD"
    )

    val result = currencyInfo.asFiatEntity()

    assertEquals(expected, result)
  }

  @Test
  fun `test FiatCurrencyEntity to CurrencyInfo mapping`() = runTest {
    val fiatEntity = FiatCurrencyEntity(
      id = "EUR",
      name = "Euro",
      symbol = "$",
      code = "EUR"
    )

    val expected = CurrencyInfo(
      id = "EUR",
      name = "Euro",
      symbol = "$",
      code = "EUR"
    )

    val result = fiatEntity.asExternalModel()

    assertEquals(expected, result)
  }

  @Test
  fun `test CryptoCurrencyEntity to CurrencyInfo mapping`() = runTest {
    val cryptoEntity = CryptoCurrencyEntity(
      id = "ETH",
      name = "Ethereum",
      symbol = "ETH"
    )

    val expected = CurrencyInfo(
      id = "ETH",
      name = "Ethereum",
      symbol = "ETH",
      code = null
    )

    val result = cryptoEntity.asExternalModel()

    assertEquals(expected, result)
  }

  @Test
  fun `test CombinedCurrencyEntity to CurrencyInfo mapping`() = runTest {
    val combinedEntity = CombinedCurrencyEntity(
      id = "LTC",
      name = "Litecoin",
      symbol = "LTC"
    )

    val expected = CurrencyInfo(
      id = "LTC",
      name = "Litecoin",
      symbol = "LTC"
    )

    val result = combinedEntity.asExternalModel()

    assertEquals(expected, result)
  }
}