package id.niteroomcreation.learningprogressstory.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.rules.TestWatcher

/**
 * Created by Septian Adi Wijaya on 18/02/2023.
 * please be sure to add credential if you use people's code
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MainCoroutineRule: TestWatcher(), TestCoroutineScope by TestCoroutineScope() {
}