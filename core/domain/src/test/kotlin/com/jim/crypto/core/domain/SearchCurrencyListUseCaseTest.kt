//@file:OptIn(ExperimentalCoroutinesApi::class)
//
//package com.jim.crypto.core.domain
//
//import androidx.paging.PagingData
//import app.cash.turbine.test
//import com.jim.crypto.core.data.repository.CurrencyRepository
//import com.jim.crypto.core.domain.usecase.SearchInMemoryCurrencyListUseCase
//import com.jim.crypto.core.model.data.CurrencyInfo
//import io.mockk.coEvery
//import io.mockk.mockk
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.flowOn
//import kotlinx.coroutines.test.StandardTestDispatcher
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.runTest
//import kotlinx.coroutines.test.setMain
//import org.junit.After
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Test
//
//class SearchCurrencyListUseCaseTest {
//
//  private lateinit var useCase: SearchInMemoryCurrencyListUseCase
//  private val repository: CurrencyRepository = mockk()
//  private val testDispatcher = StandardTestDispatcher()
//
//  @Before
//  fun setup() {
//    Dispatchers.setMain(testDispatcher)
//    useCase = SearchInMemoryCurrencyListUseCase(repository)
//  }
//
//  @After
//  fun tearDown() {
//    Dispatchers.resetMain()
//  }
//
//  @Test
//  fun `invoke should return paging data from repository`() = runTest {
//    val mockQuery = "BTC"
//    val mockData = PagingData.from(listOf(CurrencyInfo("BTC", "Bitcoin", "BTC")))
//    coEvery { repository.getPagedItems(mockQuery) } returns flow { emit(mockData) }
//
//    val resultFlow = useCase(mockQuery)
//
//    resultFlow.flowOn(testDispatcher).test {
//      assertEquals(mockData, awaitItem())
//      awaitComplete()
//    }
//  }
//
//  @Test
//  fun `invoke should call repository with correct query`() = runTest {
//    val mockQuery = "BTC"
//    val mockData = PagingData.empty<CurrencyInfo>()
//    coEvery { repository.getPagedItems(mockQuery) } returns flow { emit(mockData) }
//
//    val resultFlow = useCase(mockQuery)
//
//    resultFlow.test {
//      assertEquals(mockData, awaitItem())
//      awaitComplete()
//    }
//  }
//}
