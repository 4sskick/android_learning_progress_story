package id.niteroomcreation.learningprogressstory.data.repository

import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.auth.register.RegisterResponse

/**
 * Created by Septian Adi Wijaya on 29/01/2023.
 * please be sure to add credential if you use people's code
 */
interface RegisterRepositoryImpl {

    suspend fun register(name: String, email: String, password: String): Resource<RegisterResponse>
}