package id.niteroomcreation.learningprogressstory.util

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