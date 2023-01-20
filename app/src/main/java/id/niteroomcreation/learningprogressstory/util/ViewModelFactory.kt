package id.niteroomcreation.learningprogressstory.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.niteroomcreation.learningprogressstory.data.LoginDataSource
import id.niteroomcreation.learningprogressstory.data.LoginRepository
import id.niteroomcreation.learningprogressstory.presenter.feature.auth.login.LoginViewModel

/**
 * Created by Septian Adi Wijaya on 14/01/2023.
 * please be sure to add credential if you use people's code
 */
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    companion object {

        @Volatile
        private lateinit var instance: ViewModelFactory


        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): ViewModelFactory {
            if (instance == null) {
                instance = ViewModelFactory()
            }

            return instance
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(LoginViewModel::class.java))
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource()
                )
            ) as T

        throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
    }
}