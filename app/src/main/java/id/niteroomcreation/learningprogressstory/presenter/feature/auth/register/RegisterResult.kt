package id.niteroomcreation.learningprogressstory.presenter.feature.auth.register

import id.niteroomcreation.learningprogressstory.domain.model.auth.register.RegisterResponse

/**
 * Created by Septian Adi Wijaya on 30/01/2023.
 * please be sure to add credential if you use people's code
 */
data class RegisterResult(
    val success: RegisterResponse? = null,
    val error: Int? = null
)