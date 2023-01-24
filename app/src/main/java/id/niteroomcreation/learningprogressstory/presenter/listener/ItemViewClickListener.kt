package id.niteroomcreation.learningprogressstory.presenter.listener


/**
 * Created by Septian Adi Wijaya on 24/01/2023.
 * please be sure to add credential if you use people's code
 */
interface ItemViewClickListener<T> {
    fun onItemClicked(model: T)
}