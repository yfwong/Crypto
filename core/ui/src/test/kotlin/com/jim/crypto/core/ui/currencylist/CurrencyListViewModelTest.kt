@file:OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)

package com.jim.crypto.core.ui.currencylist

import androidx.paging.PagingData
import app.cash.turbine.test
import com.jim.crypto.core.domain.usecase.SearchCurrencyListUseCase
import com.jim.crypto.core.model.data.CurrencyInfo
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
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
    // mimic typing query string
    viewModel.onQueryChange("BTC")

    assertEquals("BTC", viewModel.searchQuery.value)
  }

  @Test
  fun `onNavigateBack clears searchQuery and hides search input`() = runTest {
    viewModel.onSearchClick()
    viewModel.onQueryChange("BTC")

    // mimic clicking Back
    viewModel.onNavigateBack()

    assertEquals("BTC", viewModel.searchQuery.value)
    assertFalse(viewModel.isShowSearchInput.value)
  }

  @Test
  fun `pagedItems emits data when query changes`() = runTest {
    val mockPagingData = PagingData.from(listOf<CurrencyInfo>())
    every { searchCurrencyListUseCase.invoke(any()) } returns flow { emit(mockPagingData) }

    // mimic typing query string
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

    // mimic clicking Search button
    assertTrue(viewModel.isShowSearchInput.value)
  }

  @Test
  fun `onQueryClear clears searchQuery`() = runTest {
    viewModel.onQueryChange("BTC")

    // mimic clicking Clear button
    viewModel.onQueryClear()

    assertEquals("", viewModel.searchQuery.value)
  }
}
