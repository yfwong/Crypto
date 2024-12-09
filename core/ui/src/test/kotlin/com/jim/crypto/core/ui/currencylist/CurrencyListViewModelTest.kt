@file:OptIn(ExperimentalCoroutinesApi::class)

package com.jim.crypto.core.ui.currencylist

import androidx.paging.PagingData
import app.cash.turbine.test
import com.jim.crypto.core.domain.usecase.SearchCurrencyListUseCase
import com.jim.crypto.core.model.data.CurrencyInfo
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CurrencyListViewModelTest {

  private lateinit var viewModel: CurrencyListViewModel
  private val searchCurrencyListUseCase: SearchCurrencyListUseCase = mockk(relaxed = true)
  private val testDispatcher = StandardTestDispatcher()

  @Before
  fun setup() {
    Dispatchers.setMain(testDispatcher)
    viewModel = CurrencyListViewModel(searchCurrencyListUseCase)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `onQueryChange updates searchQuery`() = runTest {
    // type a query string
    viewModel.onQueryChange("BTC")

    assertEquals("BTC", viewModel.searchQuery.value)
  }

  @Test
  fun `onNavigateBack clears searchQuery and hides search input`() = runTest {
    // click search button
    viewModel.onSearchClick()
    // type something in text input
    viewModel.onQueryChange("BTC")

    // click Back
    viewModel.onNavigateBack()

    assertEquals("", viewModel.searchQuery.value)
    assertFalse(viewModel.isShowSearchInput.value)
  }

  @Test
  fun `pagedItems emits data when query changes`() = runTest {
    val mockPagingData = PagingData.from(listOf<CurrencyInfo>())
    every { searchCurrencyListUseCase.invoke(any()) } returns flow { emit(mockPagingData) }

    // type a query string
    viewModel.onQueryChange("BTC")
    val job = launch {
      viewModel.pagedItems.test {
        assertEquals(mockPagingData, awaitItem())
        cancelAndIgnoreRemainingEvents()
      }
    }
    advanceTimeBy(CurrencyListViewModel.SEARCH_DEBOUNCE_MS)

    job.cancel()
  }

  @Test
  fun `onSearchClick shows search input`() = runTest {
    viewModel.onSearchClick()

    // click Search button
    assertTrue(viewModel.isShowSearchInput.value)
  }

  @Test
  fun `onQueryClear clears searchQuery`() = runTest {
    viewModel.onQueryChange("BTC")

    // click Clear button
    viewModel.onQueryClear()

    assertEquals("", viewModel.searchQuery.value)
  }
}
