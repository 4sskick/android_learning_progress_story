package id.niteroomcreation.learningprogressstory.domain.service

import id.niteroomcreation.learningprogressstory.domain.model.auth.login.LoginResponse
import id.niteroomcreation.learningprogressstory.domain.model.auth.register.RegisterResponse
import id.niteroomcreation.learningprogressstory.domain.model.stories.CreateStoryResponse
import id.niteroomcreation.learningprogressstory.domain.model.stories.StoriesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Septian Adi Wijaya on 29/01/2023.
 * please be sure to add credential if you use people's code
 */
interface APIService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<RegisterResponse>

    @GET("stories")
    @Headers("Content-Type:application/json; charset=UTF-8")
    suspend fun getStories(
    ): Response<StoriesResponse>

    @Multipart
    @POST("stories")
    suspend fun createStory(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
        @Part("lat") lat: Float,
        @Part("lon") lon: Float,
    ): Response<CreateStoryResponse>
}