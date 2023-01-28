package id.niteroomcreation.learningprogressstory.domain.service

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Septian Adi Wijaya on 29/01/2023.
 * please be sure to add credential if you use people's code
 */
interface Dispatcher {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}