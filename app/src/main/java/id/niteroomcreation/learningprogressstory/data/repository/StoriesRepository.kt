package id.niteroomcreation.learningprogressstory.data.repository

import id.niteroomcreation.learningprogressstory.data.datasource.StoriesDataSource
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.stories.CreateStoryResponse
import id.niteroomcreation.learningprogressstory.domain.model.stories.StoriesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

/**
 * Created by Septian Adi Wijaya on 29/01/2023.
 * please be sure to add credential if you use people's code
 */
class StoriesRepository(private val dataSource: StoriesDataSource) : StoriesRepositoryImpl {

    override suspend fun getAll(): Resource<StoriesResponse> {
        return dataSource.getAll()
    }

    override suspend fun getDetailById(): Resource<StoriesResponse> {
        return Resource.Loading
    }

    override suspend fun postStory(
        imageFile: MultipartBody.Part,
        desc: RequestBody
    ): Resource<CreateStoryResponse> {
        return dataSource.postStory(imageFile, desc)
    }
}