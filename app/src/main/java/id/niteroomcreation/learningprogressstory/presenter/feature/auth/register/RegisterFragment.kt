package id.niteroomcreation.learningprogressstory.presenter.feature.auth.register

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import id.niteroomcreation.learningprogressstory.databinding.FRegisterBinding
import id.niteroomcreation.learningprogressstory.presenter.base.BaseFragment
import id.niteroomcreation.learningprogressstory.presenter.feature.auth.AuthInterface

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

        binding.registerBack.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                listener.onGotoLogin()
            }
        })

        binding.registerButton.setOnClickListener(object :OnClickListener{
            override fun onClick(v: View?) {
                listener.onRegisterOperation()
            }
        })
    }

    override fun setupObserver() {
        mViewModel = obtainViewModel(this, RegisterViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is RegisterInterface)
            listener = context as RegisterInterface
        else
            throw RuntimeException("${context.toString()} need to implement RegisterInterface")
    }

    interface RegisterInterface : AuthInterface {
        fun onRegisterOperation()
    }
}