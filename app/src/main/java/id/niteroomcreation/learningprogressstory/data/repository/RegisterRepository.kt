package id.niteroomcreation.learningprogressstory.data.repository

import id.niteroomcreation.learningprogressstory.R
import id.niteroomcreation.learningprogressstory.data.datasource.RegisterDataSource
import id.niteroomcreation.learningprogressstory.domain.di.Injection
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.auth.register.RegisterResponse

/**
 * Created by Septian Adi Wijaya on 29/01/2023.
 * please be sure to add credential if you use people's code
 */
class RegisterRepository(val dataSource: RegisterDataSource) : RegisterRepositoryImpl {
    override suspend fun register(
        name: String,
        email: String,
        password: String,
        passwordConfirm: String
    ): Resource<RegisterResponse> {
        if (password != passwordConfirm)
            return Resource.Error(
                Injection.provideAppContext().resources.getString(R.string.register_failed_password),
                null
            )
        return dataSource.register(name, email, password, passwordConfirm)
    }
}