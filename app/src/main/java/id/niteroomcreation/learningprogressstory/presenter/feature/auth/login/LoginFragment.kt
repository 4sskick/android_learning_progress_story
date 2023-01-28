package id.niteroomcreation.learningprogressstory.presenter.feature.auth.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.lifecycle.Observer
import id.niteroomcreation.learningprogressstory.databinding.FLoginBinding
import id.niteroomcreation.learningprogressstory.presenter.base.BaseFragment
import id.niteroomcreation.learningprogressstory.presenter.feature.auth.AuthInterface

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

        binding.loginButton.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
//                listener.onLoginOperation()


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

    override fun setupObserver() {
        mViewModel = obtainViewModel(this, LoginViewModel::class.java)

//        val username = binding.username
//        val password = binding.password
//        val login = binding.login
//        val loading = binding.loading
//
//        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
//            .get(LoginViewModel::class.java)
//
//        loginViewModel.loginFormState.observe(this@LoginFragment, Observer {
//            val loginState = it ?: return@Observer
//
//            // disable login button unless both username / password is valid
//            login.isEnabled = loginState.isDataValid
//
//            if (loginState.usernameError != null) {
//                username.error = getString(loginState.usernameError)
//            }
//            if (loginState.passwordError != null) {
//                password.error = getString(loginState.passwordError)
//            }
//        })
//
        mViewModel.loginResult.observe(this@LoginFragment, Observer {
            val loginResult = it ?: return@Observer

            dismissLoading()
//            loading.visibility = View.GONE
            if (loginResult.error != null) {
//                showLoginFailed(loginResult.error)
                showMessage(loginResult.error)
            }
            if (loginResult.success != null) {
//                updateUiWithUser(loginResult.success)
                showMessage("${loginResult.success.displayName} welcome")
            }
//            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
//            finish()
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