package id.niteroomcreation.learningprogressstory.util

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText
import androidx.annotation.RequiresApi
import java.io.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by Septian Adi Wijaya on 26/01/2023.
 * please be sure to add credential if you use people's code
 */


//@RequiresApi(Build.VERSION_CODES.O)
fun String.dateFormatWithTime(): String {
    if (this.isBlank())
        return ""

    val instant = Instant.parse(this)
    val formatter = DateTimeFormatter.ofPattern(Constants.UTC_FORMAT_)
        .withZone(ZoneId.of(TimeZone.getDefault().id))
    return formatter.format(instant)
}

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


val currentTimeStamp: String = SimpleDateFormat(
    Constants.CREATED_DATE_FORMAT,
    Locale.US
).format(System.currentTimeMillis())

fun storeTempFile(context: Context): File {
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(currentTimeStamp, ".jpg", storageDir)
}

fun uriToFile(selectedImg: Uri, context: Context): File {
    val contentResolver: ContentResolver = context.contentResolver
    val myFile = storeTempFile(context)

    val inputStream = contentResolver.openInputStream(selectedImg) as InputStream
    val outputStream: OutputStream = FileOutputStream(myFile)
    val buf = ByteArray(Constants.SIZE_BYTE_ARRAY)
    var len: Int

    while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
    outputStream.close()
    inputStream.close()

    return myFile
}

fun reduceFileImage(file: File): File {
    val bitmap = BitmapFactory.decodeFile(file.path)
    var compressQuality = 100
    var streamLength: Int

    do {
        val bmpStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
        val bmpPicByteArray = bmpStream.toByteArray()
        streamLength = bmpPicByteArray.size
        compressQuality -= 5
    } while (streamLength > Constants.STREAM_LENGTH)

    bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, FileOutputStream(file))
    return file
}

fun isValidEmail(email: String): Boolean {
    return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidMinLength(password: String): Boolean {
    return !TextUtils.isEmpty(password) && password.length >= Constants.MIN_LENGTH_PASSWORD
}
