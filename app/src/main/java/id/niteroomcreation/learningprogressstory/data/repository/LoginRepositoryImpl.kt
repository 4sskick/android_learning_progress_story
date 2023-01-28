package id.niteroomcreation.learningprogressstory.data.repository

import id.niteroomcreation.learningprogressstory.domain.model.auth.login.LoggedInUser
import id.niteroomcreation.learningprogressstory.domain.model.auth.login.Result

/**
 * Created by Septian Adi Wijaya on 29/01/2023.
 * please be sure to add credential if you use people's code
 */
interface LoginRepositoryImpl {

    suspend fun doLogin(username: String, password: String): Result<LoggedInUser>

}