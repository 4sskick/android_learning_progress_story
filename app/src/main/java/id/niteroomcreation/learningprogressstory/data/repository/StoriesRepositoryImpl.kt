package id.niteroomcreation.learningprogressstory.data.repository

import androidx.paging.PagingData
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.stories.CreateStoryResponse
import id.niteroomcreation.learningprogressstory.domain.model.stories.StoriesResponse
import id.niteroomcreation.learningprogressstory.domain.model.stories.Story
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import okhttp3.MultipartBody
import okhttp3.RequestBody

/**
 * Created by Septian Adi Wijaya on 29/01/2023.
 * please be sure to add credential if you use people's code
 */
interface StoriesRepositoryImpl {

    suspend fun getAll_(): Flow<PagingData<Story>> {
        return emptyFlow()
    }

    suspend fun getAll(): Resource<StoriesResponse>

    suspend fun getDetailById():Resource<StoriesResponse>

    suspend fun postStory(imageFile: MultipartBody.Part, desc: RequestBody):Resource<CreateStoryResponse>
}