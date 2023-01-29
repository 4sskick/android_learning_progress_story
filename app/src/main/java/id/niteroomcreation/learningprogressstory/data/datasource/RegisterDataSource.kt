package id.niteroomcreation.learningprogressstory.data.datasource

import id.niteroomcreation.learningprogressstory.data.repository.RegisterRepositoryImpl
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.Resource.Error
import id.niteroomcreation.learningprogressstory.domain.model.Resource.Success
import id.niteroomcreation.learningprogressstory.domain.model.auth.register.RegisterResponse
import id.niteroomcreation.learningprogressstory.domain.service.APIConfig
import java.io.IOException

/**
 * Created by Septian Adi Wijaya on 29/01/2023.
 * please be sure to add credential if you use people's code
 */
class RegisterDataSource : RegisterRepositoryImpl {

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): Resource<RegisterResponse> = try {
        val response = APIConfig.getApi().register(name, email, password)
        val result = response.body()

        if (response.isSuccessful && result != null)
            Success(result)
        else
            Error(IOException(response.message()))
    } catch (e: Exception) {
        Error(IOException("An error happen", e))
    }
}