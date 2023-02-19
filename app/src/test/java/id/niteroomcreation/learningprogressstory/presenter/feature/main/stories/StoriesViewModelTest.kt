package id.niteroomcreation.learningprogressstory.presenter.feature.main.stories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.niteroomcreation.learningprogressstory.data.repository.StoriesRepository
import id.niteroomcreation.learningprogressstory.domain.model.stories.Story
import id.niteroomcreation.learningprogressstory.util.DataDummy
import id.niteroomcreation.learningprogressstory.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
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

    @Test
    fun `get stories not null & return success`() = runTest {
        val data = StoriesPagingSource.snapshot(DataDummy.generateDummyStory().listStory)
        val expect = emptyFlow<PagingData<Story>>()
        expect.collectLatest {  }
    }

    @Test
    fun `get stories not null & return error`() = runTest {
    }
}

class StoriesPagingSource : PagingSource<Int, Flow<List<Story>>>() {

    companion object {
        fun snapshot(items: List<Story>): PagingData<Story> {
            return PagingData.from(items)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Flow<List<Story>>>): Int? {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Flow<List<Story>>> {
        return LoadResult.Page(data = emptyList(), prevKey = 0, nextKey = 1)
    }

}