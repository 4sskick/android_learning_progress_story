package id.niteroomcreation.learningprogressstory.presenter.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable


/**
 * Created by Septian Adi Wijaya on 14/01/2023.
 * please be sure to add credential if you use people's code
 */
interface IBaseView {
    fun onInflateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        return null
    }

    fun onCreateInside() {

    }

    @LayoutRes
    fun contentLayout(): Int {
        return 0
    }

    fun initUI()

    fun setupObserver() {}

    fun destroyUI() {

    }

    fun showLoading(title: String?, desc: String?)

    fun showLoading()

    fun dismissLoading()

    fun showMessage(message: String?)

    fun showMessage(resId: Int)
}