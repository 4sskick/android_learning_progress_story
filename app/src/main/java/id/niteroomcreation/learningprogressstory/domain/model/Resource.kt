package id.niteroomcreation.learningprogressstory.domain.model

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Resource<out T : Any> {

    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val exception: Exception) : Resource<Nothing>()
    object Loading : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            else -> {
                "Loading[]"
            }
        }
    }
}