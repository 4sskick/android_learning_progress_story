package id.niteroomcreation.learningprogressstory.data.repository

import id.niteroomcreation.learningprogressstory.R
import id.niteroomcreation.learningprogressstory.data.datasource.LoginDataSource
import id.niteroomcreation.learningprogressstory.domain.di.Injection
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.auth.login.LoginResponse

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) : LoginRepositoryImpl {

    override suspend fun login(username: String, password: String): Resource<LoginResponse> {
        if (username.isNullOrEmpty())
            return Resource.Error(
                Injection.provideAppContext().resources.getString(R.string.info_failed_username),
                null
            )
        if (password.isNullOrEmpty())

            return Resource.Error(
                Injection.provideAppContext().resources.getString(R.string.info_failed_password),
                null
            )

        return dataSource.login(username, password)
    }
}