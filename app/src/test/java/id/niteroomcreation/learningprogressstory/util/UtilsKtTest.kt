package id.niteroomcreation.learningprogressstory.util

import org.junit.Assert
import org.junit.Test

/**
 * Created by Septian Adi Wijaya on 09/02/2023.
 * please be sure to add credential if you use people's code
 */
class UtilsKtTest {

    @Test
    fun dateFormatWithTime() {
        val current = "2022-02-02T10:10:10Z"
        Assert.assertEquals("02 Feb 2022 | 17:10", current.dateFormatWithTime())
    }
}