package id.niteroomcreation.learningprogressstory.presenter.feature.auth.register

import id.niteroomcreation.learningprogressstory.data.repository.RegisterRepositoryImpl
import id.niteroomcreation.learningprogressstory.domain.service.Dispatcher
import id.niteroomcreation.learningprogressstory.presenter.base.BaseViewModel

/**
 * Created by Septian Adi Wijaya on 20/01/2023.
 * please be sure to add credential if you use people's code
 */
class RegisterViewModel(
    private val registerRepository: RegisterRepositoryImpl,
    private val dispatcher: Dispatcher
) : BaseViewModel() {
}