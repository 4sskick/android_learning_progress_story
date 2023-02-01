package id.niteroomcreation.learningprogressstory.util

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Septian Adi Wijaya on 26/01/2023.
 * please be sure to add credential if you use people's code
 */

fun String.dateFormatted(): String {

    if (this.isBlank())
        return ""

    val formatter = SimpleDateFormat(Constants.UTC_FORMAT)
    formatter.timeZone = TimeZone.getTimeZone(Constants.UTC_TIME_ZONE)

    //`this` represent the value which String uses
    val date = formatter.parse(this) as Date
    val dateFormatter = SimpleDateFormat(Constants.CREATED_DATE_FORMAT)
    dateFormatter.timeZone = TimeZone.getDefault()

    return dateFormatter.format(date)
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

fun isValidEmail(email: String): Boolean {
    return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidMinLength(password: String): Boolean {
    return !TextUtils.isEmpty(password) && password.length >= Constants.MIN_LENGTH_PASSWORD
}
