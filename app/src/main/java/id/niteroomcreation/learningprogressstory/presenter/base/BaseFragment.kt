package id.niteroomcreation.learningprogressstory.presenter.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import id.niteroomcreation.learningprogressstory.util.ViewModelFactory

/**
 * Created by Septian Adi Wijaya on 14/01/2023.
 * please be sure to add credential if you use people's code
 */
abstract class BaseFragment<VM : BaseViewModel> : Fragment(), IBaseView {

    companion object {
        val TAG = BaseFragment::class.java.simpleName
    }

    protected lateinit var mViewModel: VM
    private lateinit var activity: BaseActivity

    fun obtainViewModel(owner: ViewModelStoreOwner, vm: Class<VM>): VM {
        return ViewModelProvider(
            owner = owner,
            factory = ViewModelFactory.getInstance(requireContext())
        ).get(vm)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        destroyUI()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity)
            activity = context as BaseActivity
        else
            throw RuntimeException("attaching attach() failed on ${context::class.java.simpleName}")
    }

    override fun showLoading(title: String?, desc: String?) {
        activity.showLoading(title, desc)
    }

    override fun showLoading() {
        activity.showLoading()
    }

    override fun dismissLoading() {
        activity.dismissLoading()
    }

    override fun showMessage(message: String?) {
        activity.showMessage(message)
    }

    override fun showMessage(resId: Int) {
        activity.showMessage(resId)
    }
}