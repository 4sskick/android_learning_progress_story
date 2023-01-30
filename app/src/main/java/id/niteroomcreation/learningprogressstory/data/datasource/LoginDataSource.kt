package id.niteroomcreation.learningprogressstory.data.datasource

import id.niteroomcreation.learningprogressstory.data.repository.LoginRepositoryImpl
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.auth.login.LoginResponse
import id.niteroomcreation.learningprogressstory.domain.service.APIConfig
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource : LoginRepositoryImpl {

    override suspend fun login(
        email: String,
        password: String
    ): Resource<LoginResponse> = try {
        val response = APIConfig.getApi().login(email, password)
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

    fun logout() {
        // TODO: revoke authentication
    }
}