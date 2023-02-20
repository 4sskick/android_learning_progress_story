package id.niteroomcreation.learningprogressstory.presenter.feature.main.stories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import androidx.recyclerview.widget.ListUpdateCallback
import id.niteroomcreation.learningprogressstory.data.repository.StoriesRepository
import id.niteroomcreation.learningprogressstory.domain.model.stories.Story
import id.niteroomcreation.learningprogressstory.util.DataDummy
import id.niteroomcreation.learningprogressstory.util.MainCoroutineRule
import id.niteroomcreation.learningprogressstory.util.collectData
import id.niteroomcreation.learningprogressstory.util.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Septian Adi Wijaya on 19/02/2023.
 * please be sure to add credential if you use people's code
 */
@RunWith(MockitoJUnitRunner::class)
class StoriesViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRules = MainCoroutineRule()

    @Mock
    private lateinit var storyRepository: StoriesRepository
    private lateinit var storiesViewModel: StoriesViewModel

    @Before
    fun setup(){
        storiesViewModel = StoriesViewModel(storyRepository)
    }

    @Test
    fun `get stories not null & return success`() = runTest {
        val data: PagingData<Story> =
            StoriesPagingSource.snapshot(DataDummy.generateDummyStory().listStory)
        val expect = MutableLiveData<PagingData<Story>>()

        expect.value = data
        `when`(storyRepository.getAll_()).thenReturn(expect)

        val actual:PagingData<Story> = storiesViewModel.getStories().getOrAwaitValue()

        val differ = AsyncPagingDataDiffer(
            diffCallback = StoriesAdapterPaging.DIFF_CALLBACK,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main,
        )

        differ.submitData(actual)

        Assert.assertNotNull(differ.snapshot())
        Assert.assertEquals(DataDummy.generateDummyStory().listStory, differ.snapshot())
        Assert.assertEquals(DataDummy.generateDummyStory().listStory.size, differ.snapshot().size)
        Assert.assertEquals(DataDummy.generateDummyStory().listStory[0].name, differ.snapshot()[0]?.name)
    }

    @Test
    fun `get stories empty & return zero`() = runTest{
        val data:PagingData<Story> = StoriesPagingSource.snapshot(listOf())
        val expect = MutableLiveData<PagingData<Story>>()

        expect.value = data
        `when`(storyRepository.getAll_()).thenReturn(expect)

        val actual:PagingData<Story> = storiesViewModel.getStories().getOrAwaitValue()

        val differ = AsyncPagingDataDiffer(
            diffCallback = StoriesAdapterPaging.DIFF_CALLBACK,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main
        )

        differ.submitData(actual)

        Assert.assertEquals(0, differ.snapshot().size)
    }

}

val noopListUpdateCallback = object : ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
}

class StoriesPagingSource : PagingSource<Int, List<Story>>() {

    companion object {
        fun snapshot(items: List<Story>): PagingData<Story> {
            return PagingData.from(items)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, List<Story>>): Int? {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, List<Story>> {
        return LoadResult.Page(data = emptyList(), prevKey = 0, nextKey = 1)
    }

}