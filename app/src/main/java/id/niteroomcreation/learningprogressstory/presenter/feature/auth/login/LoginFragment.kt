package id.niteroomcreation.learningprogressstory.presenter.feature.auth.login

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.lifecycle.Observer
import id.niteroomcreation.learningprogressstory.databinding.FLoginBinding
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.presenter.base.BaseFragment
import id.niteroomcreation.learningprogressstory.presenter.feature.auth.AuthInterface
import id.niteroomcreation.learningprogressstory.presenter.feature.auth.register.RegisterFragment
import id.niteroomcreation.learningprogressstory.util.LogHelper
import id.niteroomcreation.learningprogressstory.util.PrefKey

class LoginFragment : BaseFragment<LoginViewModel>() {

    companion object {
        val TAG = LoginFragment::class.java.simpleName

        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    private lateinit var binding: FLoginBinding
    private lateinit var listener: LoginInterface

    override fun onInflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initUI() {
        setupObserver()
        setupAnimation()

        binding.loginButton.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {

                showLoading()
                mViewModel.login(
                    binding.loginEmailInputEdit.text.toString(),
                    binding.loginPasswInputEdit.text.toString()
                )
            }
        })

        binding.registerButton.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                listener.onGotoRegister()
            }
        })
    }

    private fun setupAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -40f, 40f).apply {
            duration = 2000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
    }

    override fun setupObserver() {
        mViewModel = obtainViewModel(this, LoginViewModel::class.java)
        mViewModel.loginResult.observe(this@LoginFragment, Observer {

            LogHelper.j(RegisterFragment.TAG, it)

            when (it) {
                is Resource.Error -> {
                    dismissLoading()

                    prefApp.setBoolean(PrefKey.LOGIN_FLAG, false)

                    showMessage(it.message)
                }
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    dismissLoading()

                    prefUser.setString(PrefKey.LOGIN_TOKEN, it.data.loginResult.token)
                    prefUser.setString(PrefKey.LOGIN_USERID, it.data.loginResult.userId)
                    prefUser.setString(PrefKey.LOGIN_NAME, it.data.loginResult.name)
                    prefApp.setBoolean(PrefKey.LOGIN_FLAG, true)

                    listener.onLoginOperation()

                }
            }
        })
//
//        username.afterTextChanged {
//            loginViewModel.loginDataChanged(
//                username.text.toString(),
//                password.text.toString()
//            )
//        }
//
//        password.apply {
//            afterTextChanged {
//                loginViewModel.loginDataChanged(
//                    username.text.toString(),
//                    password.text.toString()
//                )
//            }
//
//            setOnEditorActionListener { _, actionId, _ ->
//                when (actionId) {
//                    EditorInfo.IME_ACTION_DONE ->
//                        loginViewModel.login(
//                            username.text.toString(),
//                            password.text.toString()
//                        )
//                }
//                false
//            }
//
//            login.setOnClickListener {
//                loading.visibility = View.VISIBLE
//                loginViewModel.login(username.text.toString(), password.text.toString())
//            }
//        }
    }

//    private fun updateUiWithUser(model: LoggedInUserView) {
//        val welcome = getString(R.string.welcome)
//        val displayName = model.displayName
//        // TODO : initiate successful logged in experience
//        Toast.makeText(
//            context,
//            "$welcome $displayName",
//            Toast.LENGTH_LONG
//        ).show()
//    }
//
//    private fun showLoginFailed(@StringRes errorString: Int) {
//        Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show()
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is LoginInterface)
            listener = context as LoginInterface
        else
            throw RuntimeException("${context.toString()} need to implement LoginInterface")
    }

    interface LoginInterface : AuthInterface {
        fun onLoginOperation()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
//fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
//    this.addTextChangedListener(object : TextWatcher {
//        override fun afterTextChanged(editable: Editable?) {
//            afterTextChanged.invoke(editable.toString())
//        }
//
//        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//
//        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
//    })
//}