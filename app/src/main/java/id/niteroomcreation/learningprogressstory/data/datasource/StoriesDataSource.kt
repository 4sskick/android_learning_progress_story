package id.niteroomcreation.learningprogressstory.data.datasource

import id.niteroomcreation.learningprogressstory.data.repository.StoriesRepositoryImpl
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.stories.CreateStoryResponse
import id.niteroomcreation.learningprogressstory.domain.model.stories.StoriesResponse
import id.niteroomcreation.learningprogressstory.domain.service.APIConfig
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.IOException

/**
 * Created by Septian Adi Wijaya on 31/01/2023.
 * please be sure to add credential if you use people's code
 */
class StoriesDataSource : StoriesRepositoryImpl {

    override suspend fun getAll(): Resource<StoriesResponse> = try {
        val response = APIConfig.getApi().getStories()
        val result = response.body()

        if (response.isSuccessful && result != null)
            if (result.error)
                Resource.Error(result.message, null)
            else
                Resource.Success(result)
        else
            Resource.Error(response.errorBody()!!.string(), IOException(response.message()))

    } catch (e: Exception) {
        Resource.Error("An error happen", IOException(e))
    }

    override suspend fun getAllWithLocation(): Resource<StoriesResponse> = try {
        val response = APIConfig.getApi().getStories(
            page = 1,
            size = 100,
            location = 1
        )
        val result = response.body()

        if (response.isSuccessful && result != null)
            if (result.error)
                Resource.Error(result.message, null)
            else
                Resource.Success(result)
        else
            Resource.Error(response.errorBody()!!.string(), IOException(response.message()))

    } catch (e: Exception) {
        Resource.Error("An error happen", IOException(e))
    }

    override suspend fun getDetailById(): Resource<StoriesResponse> {
        val response = APIConfig.getApi().getStories()

        return Resource.Loading
    }

    override suspend fun postStory(
        imageFile: MultipartBody.Part,
        desc: RequestBody
    ): Resource<CreateStoryResponse> = try {
        val response = APIConfig.getApi()
            .createStory(
                imageFile,
                desc,
                lat = 0f,
                lon = 0f
            )
        val result = response.body()

        if (response.isSuccessful && result != null)
            if (result.error)
                Resource.Error(result.message, null)
            else
                Resource.Success(result)
        else
            Resource.Error(response.errorBody()!!.string(), IOException(response.message()))

    } catch (e: Exception) {
        Resource.Error("An error happen", IOException(e))
    }
}