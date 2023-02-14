package id.niteroomcreation.learningprogressstory.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import id.niteroomcreation.learningprogressstory.domain.model.stories.StoriesResponse
import id.niteroomcreation.learningprogressstory.domain.model.stories.Story

/**
 * Created by Septian Adi Wijaya on 13/02/2023.
 * please be sure to add credential if you use people's code
 */
@OptIn(ExperimentalPagingApi::class)
class StoriesRemoteMediator() : RemoteMediator<Int, Story>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Story>
    ): MediatorResult {

        val page = when(loadType){
            LoadType.REFRESH -> {
                val remoteKeys = state.anchorPosition?.let { position->
                    state.closestItemToPosition(position)?.id?.let { id->

                    }
                }
            }
            LoadType.PREPEND -> {

            }
            LoadType.APPEND -> {

            }
        }


        return MediatorResult.Error(RuntimeException())
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}