package id.niteroomcreation.learningprogressstory.data.datasource

import id.niteroomcreation.learningprogressstory.domain.model.auth.login.LoggedInUser
import id.niteroomcreation.learningprogressstory.domain.model.auth.login.Result
import java.io.IOException
import java.util.*

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

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