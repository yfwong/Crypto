package com.jim.crypto.core.domain

import com.jim.crypto.core.data.repository.InMemoryCurrencyRepository
import com.jim.crypto.core.model.data.CurrencyInfo
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SetCurrencyListUseCaseTest {

  private lateinit var useCase: SetCurrencyListUseCase
  private val repository: InMemoryCurrencyRepository = mockk()

  @Before
  fun setup() {
    useCase = SetCurrencyListUseCase(repository)
  }

  @Test
  fun `invoke sets the items in the repository`() = runTest {
    // Given
    val currencies = listOf(
      CurrencyInfo(id = "BTC", name = "Bitcoin", symbol = "BTC"),
      CurrencyInfo(id = "CRO", name = "Cronos", symbol = "CRO"),
      CurrencyInfo(id = "ETH", name = "Ethereum", symbol = "ETH"),
    )

    coEvery { repository.setItems(currencies) } returns Unit

    // When
    useCase(currencies)

    // Then
    coEvery { repository.setItems(currencies) }
  }

  @Test
  fun `invoke sets empty list in the repository`() = runTest {
    // Given
    val currencies = emptyList<CurrencyInfo>()

    coEvery { repository.setItems(currencies) } returns Unit

    // When
    useCase(currencies)

    // Then
    coEvery { repository.setItems(currencies) }
  }

  @Test
  fun `setItems is called with correct data`() = runTest {
    // Given
    val currencies = listOf(
      CurrencyInfo(id = "BTC", name = "Bitcoin", symbol = "BTC"),
      CurrencyInfo(id = "CRO", name = "Cronos", symbol = "CRO"),
      CurrencyInfo(id = "ETH", name = "Ethereum", symbol = "ETH"),
    )

    coEvery { repository.setItems(currencies) } returns Unit

    // When
    useCase(currencies)

    // Then
    coEvery { repository.setItems(currencies) }
  }
}
