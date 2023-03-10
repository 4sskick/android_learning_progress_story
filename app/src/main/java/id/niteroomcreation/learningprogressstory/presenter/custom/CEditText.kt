package id.niteroomcreation.learningprogressstory.presenter.custom

import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import id.niteroomcreation.learningprogressstory.R
import id.niteroomcreation.learningprogressstory.util.isValidEmail
import id.niteroomcreation.learningprogressstory.util.isValidMinLength

/**
 * Created by Septian Adi Wijaya on 20/01/2023.
 * please be sure to add credential if you use people's code
 */
class CEditText : TextInputEditText {

    private var isEmail: Boolean = false
    private var isPassword: Boolean = false

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        preInit(attrs)
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        preInit(attrs)
        init()
    }

    private fun preInit(attrs: AttributeSet?) {
        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CEditText)

        isEmail = a.getBoolean(R.styleable.CEditText_is_email, false)
        isPassword = a.getBoolean(R.styleable.CEditText_is_password, false)

        a.recycle()
    }

    private fun init() {


        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (isEmail)
                    if (isValidEmail(s.toString()) or s.toString().isEmpty())
                        this@CEditText.error = null
                    else
                        this@CEditText.error = context.getString(R.string.invalid_email)

                if (isPassword)
                    if (isValidMinLength(s.toString()) or s.toString().isEmpty())
                        this@CEditText.error = null
                    else
                        this@CEditText.error = context.getString(R.string.invalid_password)
            }
        })
    }
}