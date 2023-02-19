package id.niteroomcreation.learningprogressstory.util

import androidx.annotation.VisibleForTesting
import androidx.paging.*
import kotlinx.coroutines.test.TestCoroutineDispatcher

/**
 * Created by Septian Adi Wijaya on 19/02/2023.
 * please be sure to add credential if you use people's code
 */
@VisibleForTesting(otherwise = VisibleForTesting.NONE)
suspend fun <T : Any> PagingData<T>.collectData(): List<T> {
    val dcb = object : DifferCallback {
        override fun onChanged(position: Int, count: Int) {}
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
    }
    val items = mutableListOf<T>()
    val dif = object : PagingDataDiffer<T>(dcb, TestCoroutineDispatcher()) {
        override suspend fun presentNewList(
            previousList: NullPaddedList<T>,
            newList: NullPaddedList<T>,
            lastAccessedIndex: Int,
            onListPresentable: () -> Unit
        ): Int? {
            for (idx in 0 until newList.size)
                items.add(newList.getFromStorage(idx))
            return null
        }
    }
    dif.collectFrom(this)
    return items
}