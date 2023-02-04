package id.niteroomcreation.learningprogressstory.data.repository

import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.stories.CreateStoryResponse
import id.niteroomcreation.learningprogressstory.domain.model.stories.StoriesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by Septian Adi Wijaya on 29/01/2023.
 * please be sure to add credential if you use people's code
 */
interface StoriesRepositoryImpl {

    suspend fun getAll(): Resource<StoriesResponse>

    suspend fun getDetailById():Resource<StoriesResponse>

    suspend fun postStory(imageFile: MultipartBody.Part, desc: RequestBody):Resource<CreateStoryResponse>
}