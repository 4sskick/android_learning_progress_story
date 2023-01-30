package id.niteroomcreation.learningprogressstory.domain.service

import id.niteroomcreation.learningprogressstory.BuildConfig
import id.niteroomcreation.learningprogressstory.domain.di.Injection
import id.niteroomcreation.learningprogressstory.util.LogHelper
import id.niteroomcreation.learningprogressstory.util.PrefKey
import id.niteroomcreation.learningprogressstory.util.PrefUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Septian Adi Wijaya on 29/01/2023.
 * please be sure to add credential if you use people's code
 */
class APIConfig {

    companion object {
        fun getApi(): APIService {
            val loggingInterceptor = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            else
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)

            val client = OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(Injection.provideNetworkInterceptor())
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://story-api.dicoding.dev/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(APIService::class.java)
        }
    }
}

class NetworkInterceptor(val prefUser: PrefUtil) : Interceptor {

    companion object {
        val TAG = NetworkInterceptor::class.java.simpleName
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        val userId = prefUser.getString(PrefKey.LOGIN_USERID, "")
        val token = prefUser.getString(PrefKey.LOGIN_TOKEN, "")

        LogHelper.e(TAG, userId, token)

        var requestBuilder: Request.Builder =
            request.newBuilder().method(request.method, request.body)

        if (userId!!.isNotEmpty()) {
            requestBuilder = requestBuilder.header("Authorization", "Bearer $token")

            LogHelper.e(TAG, requestBuilder)
        }

        return chain.proceed(requestBuilder.build())

    }

}