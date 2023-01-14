package id.niteroomcreation.learningprogressstory.presenter.base

import androidx.annotation.LayoutRes

/**
 * Created by Septian Adi Wijaya on 14/01/2023.
 * please be sure to add credential if you use people's code
 */
open interface IBaseView {

    fun onCreateInside() {

    }

    @LayoutRes
    fun contentLayout(): Int {
        return 0
    }

    fun initUI()

    fun destroyUI() {

    }

    fun showLoading(title: String?, desc: String?)

    fun showLoading()

    fun dismissLoading()

    fun showMessage(message: String?)

    fun showMessage(resId: Int)
}