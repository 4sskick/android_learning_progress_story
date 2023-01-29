package id.niteroomcreation.learningprogressstory.data.repository

import id.niteroomcreation.learningprogressstory.data.datasource.LoginDataSource
import id.niteroomcreation.learningprogressstory.domain.model.auth.login.LoggedInUser
import id.niteroomcreation.learningprogressstory.domain.model.auth.login.LoginResponse
import id.niteroomcreation.learningprogressstory.domain.model.auth.login.Result

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) : LoginRepositoryImpl {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    override suspend fun doLogin(username: String, password: String): Result<LoginResponse> {
        // handle login
//        val result = dataSource.login(username, password)
//        if (result is Result.Success) {
//            setLoggedInUser(result.data.loginResult.name)
//        }

        return dataSource.doLogin(username, password)
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}