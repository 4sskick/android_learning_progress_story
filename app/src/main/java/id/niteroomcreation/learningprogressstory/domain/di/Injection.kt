package id.niteroomcreation.learningprogressstory.domain.di

import id.niteroomcreation.learningprogressstory.data.datasource.LoginDataSource
import id.niteroomcreation.learningprogressstory.data.repository.*
import id.niteroomcreation.learningprogressstory.domain.service.Dispatcher
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

    fun provideLoginRepository(): LoginRepositoryImpl =
        LoginRepository(dataSource = LoginDataSource())

    fun provideRegisterRepository(): RegisterRepositoryImpl = RegisterRepository()

    fun provideStoriesRepository(): StoriesRepositoryImpl = StoriesRepository()
}