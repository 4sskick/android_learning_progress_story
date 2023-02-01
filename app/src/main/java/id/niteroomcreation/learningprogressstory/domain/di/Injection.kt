package id.niteroomcreation.learningprogressstory.domain.di

import android.content.Context
import id.niteroomcreation.learningprogressstory.LearningApp
import id.niteroomcreation.learningprogressstory.data.datasource.LoginDataSource
import id.niteroomcreation.learningprogressstory.data.datasource.RegisterDataSource
import id.niteroomcreation.learningprogressstory.data.datasource.StoriesDataSource
import id.niteroomcreation.learningprogressstory.data.repository.*
import id.niteroomcreation.learningprogressstory.domain.service.Dispatcher
import id.niteroomcreation.learningprogressstory.domain.service.NetworkInterceptor
import id.niteroomcreation.learningprogressstory.util.Constants
import id.niteroomcreation.learningprogressstory.util.PrefUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Septian Adi Wijaya on 29/01/2023.
 * please be sure to add credential if you use people's code
 */
object Injection {

    fun provideDispatcher(): Dispatcher = object : Dispatcher {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

    fun provideNetworkInterceptor(): NetworkInterceptor =
        NetworkInterceptor(prefUser = providePrefUser())

    fun providePrefUser(): PrefUtil = PrefUtil(provideAppContext(), Constants.PREF_USER)

    fun providePrefApp(): PrefUtil = PrefUtil(provideAppContext(), Constants.PREF_APP)

    fun provideAppContext(): Context = LearningApp.getContext()

    fun provideLoginRepository(): LoginRepositoryImpl =
        LoginRepository(dataSource = LoginDataSource())

    fun provideRegisterRepository(): RegisterRepositoryImpl =
        RegisterRepository(dataSource = RegisterDataSource())

    fun provideStoriesRepository(): StoriesRepositoryImpl =
        StoriesRepository(dataSource = StoriesDataSource())

    fun provideProfileRepository(): ProfileRepositoryImpl = ProfileRepository()
}