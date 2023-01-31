package id.niteroomcreation.learningprogressstory.data.datasource

import id.niteroomcreation.learningprogressstory.data.repository.StoriesRepositoryImpl
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.stories.StoriesResponse
import id.niteroomcreation.learningprogressstory.domain.service.APIConfig
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

    override suspend fun getDetailById(): Resource<StoriesResponse> {
        val response = APIConfig.getApi().getStories()

        return Resource.Loading
    }
}