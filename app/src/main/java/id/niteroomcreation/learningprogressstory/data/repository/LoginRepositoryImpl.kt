package id.niteroomcreation.learningprogressstory.data.repository

import id.niteroomcreation.learningprogressstory.domain.model.auth.login.LoginResponse
import id.niteroomcreation.learningprogressstory.domain.model.Resource

/**
 * Created by Septian Adi Wijaya on 29/01/2023.
 * please be sure to add credential if you use people's code
 */
interface LoginRepositoryImpl {

    suspend fun login(email: String, password: String): Resource<LoginResponse>

}