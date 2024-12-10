
import app.cash.turbine.test
import com.jim.crypto.core.domain.SearchCurrencyListUseCase
import com.jim.crypto.core.domain.SetCurrencyListUseCase
import com.jim.crypto.core.model.data.CurrencyInfo
import com.jim.crypto.core.ui.currencylist.CurrencyListViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class CurrencyListViewModelTest {

  private val testDispatcher = StandardTestDispatcher()

  private val setCurrencyListUseCase: SetCurrencyListUseCase = mockk()
  private val searchCurrencyListUseCase: SearchCurrencyListUseCase = mockk()

  private lateinit var viewModel: CurrencyListViewModel

  @Before
  fun setup() {
    // Set the main dispatcher to the test dispatcher
    Dispatchers.setMain(testDispatcher)

    // Initialize the ViewModel with mocked use cases
    viewModel = CurrencyListViewModel(setCurrencyListUseCase, searchCurrencyListUseCase)
  }

  @After
  fun tearDown() {
    // Clean up the dispatcher after tests
    Dispatchers.resetMain()
  }

  @Test
  fun `test initializeCurrencyList calls SetCurrencyListUseCase`() = runTest {
    val currencies = listOf(
      CurrencyInfo("BTC", "Bitcoin", "BTC"),
      CurrencyInfo("ETH", "Etherium", "ETH")
    )

    // Mock the behavior of setCurrencyListUseCase
    coEvery { setCurrencyListUseCase(currencies) } returns Unit

    // Initialize currency list in the ViewModel
    viewModel.initializeCurrencyList(currencies)

    // Verify that the use case was called
    coVerify { setCurrencyListUseCase(currencies) }
  }

  @Test
  fun `test searchQuery flow updates correctly on onQueryChange`() {
    viewModel.onQueryChange("Bitcoin")

    // Check if the searchQuery is updated
    assertEquals("Bitcoin", viewModel.searchQuery.value)
  }

  @Test
  fun `test Items emits search results after debounce and distinctUntilChanged`() = runTest {
    val currencyInfoList = listOf(
      CurrencyInfo("BTC", "Bitcoin", "BTC"),
      CurrencyInfo("ETH", "Ethereum", "ETH")
    )

    // Mock search use case to return results
    coEvery { searchCurrencyListUseCase(any()) } returns flowOf(currencyInfoList)

    // Simulate a query change and collect the emitted value
    viewModel.onQueryChange("Bit")
    viewModel.items.test {
      // Check that the emitted items match the expected list
      assertEquals(currencyInfoList, awaitItem())
      cancelAndIgnoreRemainingEvents()
    }
  }

  @Test
  fun `test onQueryClear resets searchQuery`() {
    // Simulate typing in a search query
    viewModel.onQueryChange("Bitcoin")

    // Clear the query and check if it's reset
    viewModel.onQueryClear()
    assertEquals("", viewModel.searchQuery.value)
  }

  @Test
  fun `test onSearchClick sets isShowSearchInput to true`() {
    viewModel.onSearchClick()

    // Check that the search input visibility is true
    assertEquals(true, viewModel.isShowSearchInput.value)
  }

  @Test
  fun `test onNavigateBack clears query and hides search input`() {
    // Simulate query change and then navigate back
    viewModel.onQueryChange("Bitcoin")
    viewModel.onNavigateBack()

    // Verify that the query is cleared and search input visibility is false
    assertEquals("", viewModel.searchQuery.value)
    assertEquals(false, viewModel.isShowSearchInput.value)
  }
}
