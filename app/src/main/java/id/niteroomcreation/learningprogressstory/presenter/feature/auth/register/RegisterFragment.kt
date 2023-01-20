package id.niteroomcreation.learningprogressstory.presenter.feature.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.niteroomcreation.learningprogressstory.databinding.FRegisterBinding
import id.niteroomcreation.learningprogressstory.presenter.base.BaseFragment
import id.niteroomcreation.learningprogressstory.util.LogHelper

/**
 * Created by Septian Adi Wijaya on 20/01/2023.
 * please be sure to add credential if you use people's code
 */
class RegisterFragment : BaseFragment<RegisterViewModel>() {

    companion object {
        val TAG = RegisterFragment::class.java.simpleName

        fun newInstance(): RegisterFragment {
            return RegisterFragment()
        }
    }

    private lateinit var binding: FRegisterBinding

    override fun onInflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initUI() {
        LogHelper.e(TAG, "here on fragment")
        setupObserver()
    }

    override fun setupObserver() {
        mViewModel = obtainViewModel(this, RegisterViewModel::class.java)
    }
}