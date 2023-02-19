package id.niteroomcreation.learningprogressstory.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.niteroomcreation.learningprogressstory.domain.model.stories.Story
import id.niteroomcreation.learningprogressstory.domain.service.APIConfig

/**
 * Created by Septian Adi Wijaya on 13/02/2023.
 * please be sure to add credential if you use people's code
 */
class StoryPagingSource : PagingSource<Int, Story>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Story>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Story> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX

            val response = APIConfig.getApi().getStories(page, params.loadSize, 0)
            if (response.isSuccessful) {
                LoadResult.Page(
                    data = response.body()?.listStory ?: emptyList(),
                    prevKey = if (page == INITIAL_PAGE_INDEX) null else page.minus(1),
                    nextKey = if (response.body()?.listStory.isNullOrEmpty()) null else page.plus(1)
                )
            } else
                LoadResult.Error(Exception("Failed load story"))
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}