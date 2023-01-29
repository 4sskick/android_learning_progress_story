package id.niteroomcreation.learningprogressstory.data.datasource

import id.niteroomcreation.learningprogressstory.domain.model.auth.login.LoggedInUser
import id.niteroomcreation.learningprogressstory.domain.model.auth.login.LoginResponse
import id.niteroomcreation.learningprogressstory.domain.model.auth.login.Result
import id.niteroomcreation.learningprogressstory.domain.service.APIConfig
import java.io.IOException
import java.util.*

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    suspend fun doLogin(
        email: String,
        password: String
    ): Result<LoginResponse> {
        return try {
            val response = APIConfig.getApi().login(email, password)
            val result = response.body()

            if (response.isSuccessful && result != null)
                Result.Success(result)
            else
                Result.Error(IOException(response.message()))

        } catch (e: Exception) {
            Result.Error(IOException("Error logged in", e))
        }
    }


    fun login(username: String, password: String): Result<LoggedInUser> = try {
        // TODO: handle loggedInUser authentication
        val fakeUser = LoggedInUser(UUID.randomUUID().toString(), "Jane Doe")
        Result.Success(fakeUser)
    } catch (e: Throwable) {
        Result.Error(IOException("Error logging in", e))
    }

    fun logout() {
        // TODO: revoke authentication
    }
}