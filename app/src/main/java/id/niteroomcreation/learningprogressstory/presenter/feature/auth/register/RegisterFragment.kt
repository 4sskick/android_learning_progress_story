package id.niteroomcreation.learningprogressstory.presenter.feature.auth.register

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import id.niteroomcreation.learningprogressstory.R
import id.niteroomcreation.learningprogressstory.databinding.FRegisterBinding
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.presenter.base.BaseFragment
import id.niteroomcreation.learningprogressstory.presenter.feature.auth.AuthInterface
import id.niteroomcreation.learningprogressstory.util.*

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
    private lateinit var listener: RegisterInterface

    override fun onInflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initUI() {
        setupObserver()
        isAllValid()

        binding.registerBack.setOnClickListener { listener.onGotoLogin() }
        binding.registerButton.setOnClickListener {
            mViewModel.register(
                binding.registerUserInputEdit.text.toString(),
                binding.registerEmailInputEdit.text.toString(),
                binding.registerPasswInputEdit.text.toString(),
                binding.registerPasswConfirmInputEdit.text.toString()
            )
        }

        binding.registerEmailInputEdit.afterTextChanged { isAllValid() }
        binding.registerPasswInputEdit.afterTextChanged { isAllValid() }
        binding.registerPasswConfirmInputEdit.afterTextChanged { isAllValid() }
    }

    private fun isAllValid() {
        val email = binding.registerEmailInputEdit.text.toString()
        val pass = binding.registerPasswInputEdit.text.toString()
        val passConfirm = binding.registerPasswConfirmInputEdit.text.toString()
        binding.registerButton.isEnabled =
            isValidEmail(email) && isValidMinLength(pass) && isValidMinLength(passConfirm)
    }

    override fun setupObserver() {
        mViewModel = obtainViewModel(this, RegisterViewModel::class.java)

        mViewModel.registerResult.observe(this, Observer {

            LogHelper.j(TAG, it)

            when (it) {
                is Resource.Error -> {
                    dismissLoading()
                    prefApp.setBoolean(PrefKey.LOGIN_FLAG, false)
                    showMessage(it.message)
                }
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    dismissLoading()
                    prefApp.setBoolean(PrefKey.LOGIN_FLAG, false)
                    showMessage(getString(R.string.info_register_succeed))

                    listener.onRegisterOperation()

                }
            }

        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is RegisterInterface)
            listener = context
        else
            throw RuntimeException("$context need to implement RegisterInterface")
    }

    interface RegisterInterface : AuthInterface {
        fun onRegisterOperation()
    }
}