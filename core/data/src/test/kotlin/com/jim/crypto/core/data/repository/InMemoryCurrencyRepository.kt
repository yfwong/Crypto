package com.jim.crypto.core.data.repository

import app.cash.turbine.test
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class InMemoryCurrencyRepositoryTest {

  private lateinit var repository: InMemoryCurrencyRepository

  @Before
  fun setup() {
    repository = InMemoryCurrencyRepositoryImpl()
  }

  @Test
  fun `getItems returns filtered list based on query`() = runTest {
    // Given
    val currencies = listOf(
      CurrencyInfo("BTC", "Bitcoin", "BTC"),
      CurrencyInfo("HKD", "HK Dollars", "$", "HKD")
    )
    repository.setItems(currencies)

    // When & Then
    repository.getItems("B").test {
      val result = awaitItem()
      assertEquals(listOf(currencies[0]), result)
      cancelAndIgnoreRemainingEvents()
    }

    // Further testing with different queries
    repository.getItems("Doll").test {
      val result = awaitItem()
      assertEquals(listOf(currencies[1]), result)
      cancelAndIgnoreRemainingEvents()
    }
  }

  @Test
  fun `getItems returns empty list when no matches`() = runTest {
    // Given
    val currencies = listOf(
      CurrencyInfo("BTC", "Bitcoin", "BTC"),
      CurrencyInfo("HKD", "HK Dollars", "$", "HKD")
    )
    repository.setItems(currencies)

    // When & Then
    repository.getItems("X").test {
      val result = awaitItem()
      assertEquals(emptyList<CurrencyInfo>(), result)
      cancelAndIgnoreRemainingEvents()
    }
  }

  @Test
  fun `getItems returns correct list for multiple filters`() = runTest {
    // Given
    val currencies = listOf(
      CurrencyInfo("BTC", "Bitcoin", "BTC"),
      CurrencyInfo("USDT", "USD Tether", "USDT"),
      CurrencyInfo("USD", "US Dollars", "$", "USD"),
    )
    repository.setItems(currencies)

    // When & Then
    repository.getItems("U").test {
      val result = awaitItem()
      assertEquals(listOf(currencies[1], currencies[2]), result)
      cancelAndIgnoreRemainingEvents()
    }

    repository.getItems("USDT").test {
      val result2 = awaitItem()
      assertEquals(listOf(currencies[1]), result2)
      cancelAndIgnoreRemainingEvents()
    }
  }

  @Test
  fun `setItems updates the currency list correctly`() = runTest {
    // Given
    val initialCurrencies = listOf(
      CurrencyInfo("BTC", "Bitcoin", "BTC")
    )
    val updatedCurrencies = listOf(
      CurrencyInfo("ETH", "Ethereum", "ETH")
    )

    // When
    repository.setItems(initialCurrencies)

    // Then
    val currentItemsBeforeUpdate = repository.getItems("").first()
    assertEquals(initialCurrencies, currentItemsBeforeUpdate)

    // When updating the list
    repository.setItems(updatedCurrencies)

    // Then after update
    val currentItemsAfterUpdate = repository.getItems("").first()
    assertEquals(updatedCurrencies, currentItemsAfterUpdate)
  }
}
