package com.jim.crypto.core.domain

import app.cash.turbine.test
import com.jim.crypto.core.data.repository.InMemoryCurrencyRepository
import com.jim.crypto.core.model.data.CurrencyInfo
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class SearchCurrencyListUseCaseTest {

  private lateinit var searchCurrencyListUseCase: SearchCurrencyListUseCase
  private val repository: InMemoryCurrencyRepository = mockk()

  @Before
  fun setup() {
    searchCurrencyListUseCase = SearchCurrencyListUseCase(repository)
  }

  @Test
  fun `invoke with valid query returns filtered list of CurrencyInfo`() = runTest {
    // Given
    val currencies = listOf(
      CurrencyInfo(id = "BTC", name = "Bitcoin", symbol = "BTC"),
      CurrencyInfo(id = "CRO", name = "Cronos", symbol = "CRO"),
      CurrencyInfo(id = "ETH", name = "Ethereum", symbol = "ETH"),
    )
    coEvery { repository.getItems("Bit") } returns flowOf(listOf(currencies[0]))

    // When & Then
    searchCurrencyListUseCase.invoke("Bit").test {
      val result = awaitItem()
      assertEquals(listOf(currencies[0]), result)
      cancelAndIgnoreRemainingEvents()
    }
  }

  @Test
  fun `invoke with empty query returns all CurrencyInfo`() = runTest {
    // Given
    val currencies = listOf(
      CurrencyInfo(id = "BTC", name = "Bitcoin", symbol = "BTC"),
      CurrencyInfo(id = "CRO", name = "Cronos", symbol = "CRO"),
      CurrencyInfo(id = "ETH", name = "Ethereum", symbol = "ETH"),
    )
    coEvery { repository.getItems("") } returns flowOf(currencies)

    // When & Then
    searchCurrencyListUseCase.invoke("").test {
      val result = awaitItem()
      assertEquals(currencies, result)
      cancelAndIgnoreRemainingEvents()
    }
  }

  @Test
  fun `invoke with query returning no matches returns empty list`() = runTest {
    // Given
    coEvery { repository.getItems("X") } returns flowOf(emptyList())

    // When & Then
    searchCurrencyListUseCase.invoke("X").test {
      val result = awaitItem()
      assertEquals(emptyList<CurrencyInfo>(), result)
      cancelAndIgnoreRemainingEvents()
    }
  }

  @Test
  fun `invoke with query and multiple matches returns correct filtered list`() = runTest {
    // Given
    val currencies = listOf(
      CurrencyInfo(id = "BTC", name = "Bitcoin", symbol = "BTC"),
      CurrencyInfo(id = "CRO", name = "Cronos", symbol = "CRO"),
      CurrencyInfo(id = "ETH", name = "Ethereum", symbol = "ETH"),
    )
    coEvery { repository.getItems("Bit") } returns flowOf(listOf(currencies[0], currencies[1]))

    // When & Then
    searchCurrencyListUseCase.invoke("Bit").test {
      val result = awaitItem()
      assertEquals(listOf(currencies[0], currencies[1]), result)
      cancelAndIgnoreRemainingEvents()
    }
  }

  @Test
  fun `invoke with query performs flow on IO dispatcher`() = runTest {
    // Given
    val currencies = listOf(
      CurrencyInfo(id = "BTC", name = "Bitcoin", symbol = "BTC"),
    )
    coEvery { repository.getItems("Bit") } returns flowOf(currencies)

    // When & Then
    searchCurrencyListUseCase.invoke("Bit").test {
      val result = awaitItem()
      assertEquals(currencies, result)
      cancelAndIgnoreRemainingEvents()
    }
  }
}
