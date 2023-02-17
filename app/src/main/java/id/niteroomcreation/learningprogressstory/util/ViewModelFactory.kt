package id.niteroomcreation.learningprogressstory.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.niteroomcreation.learningprogressstory.domain.di.Injection
import id.niteroomcreation.learningprogressstory.presenter.feature.auth.login.LoginViewModel
import id.niteroomcreation.learningprogressstory.presenter.feature.auth.register.RegisterViewModel
import id.niteroomcreation.learningprogressstory.presenter.feature.main.map.MapViewModel
import id.niteroomcreation.learningprogressstory.presenter.feature.main.profile.ProfileViewModel
import id.niteroomcreation.learningprogressstory.presenter.feature.main.stories.StoriesViewModel
import id.niteroomcreation.learningprogressstory.presenter.feature.main.stories.create.StoryCreateViewModel

/**
 * Created by Septian Adi Wijaya on 14/01/2023.
 * please be sure to add credential if you use people's code
 */
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    companion object {

        @Volatile
        private var instance: ViewModelFactory? = null


        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): ViewModelFactory {
            if (instance == null) {
                instance = ViewModelFactory()
            }

            return instance!!
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(LoginViewModel::class.java))
            return LoginViewModel(
                loginRepository = Injection.provideLoginRepository(),
//                dispatcher = Injection.provideDispatcher()
            ) as T
        else if (modelClass.isAssignableFrom(RegisterViewModel::class.java))
            return RegisterViewModel(
                registerRepository = Injection.provideRegisterRepository(),
//                dispatcher = Injection.provideDispatcher()
            ) as T
        else if (modelClass.isAssignableFrom(StoriesViewModel::class.java))
            return StoriesViewModel(
                storiesRepository = Injection.provideStoriesRepository(),
//                dispatcher = Injection.provideDispatcher()
            ) as T
        else if (modelClass.isAssignableFrom(ProfileViewModel::class.java))
            return ProfileViewModel(
                profileRepository = Injection.provideProfileRepository(),
//                dispatcher = Injection.provideDispatcher()
            ) as T
        else if (modelClass.isAssignableFrom(StoryCreateViewModel::class.java))
            return StoryCreateViewModel(
                storiesRepository = Injection.provideStoriesRepository(),
//                dispatcher = Injection.provideDispatcher()
            ) as T
        else if (modelClass.isAssignableFrom(MapViewModel::class.java))
            return MapViewModel(
                storiesRepository = Injection.provideStoriesRepository(),
//                dispatcher = Injection.provideDispatcher()
            ) as T

        throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
    }
}