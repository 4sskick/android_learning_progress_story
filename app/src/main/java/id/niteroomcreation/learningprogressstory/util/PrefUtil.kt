package id.niteroomcreation.learningprogressstory.util

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Septian Adi Wijaya on 31/01/2023.
 * please be sure to add credential if you use people's code
 */
class PrefUtil(context: Context, prefName: String?) {
    companion object {
        val TAG = PrefUtil::class.java.simpleName
    }

    private val preferences: SharedPreferences

    init {
//        if(context == null)
//            LogHelper.e(TAG, "context null");
//        else
//            LogHelper.e(TAG, "context not null");
        preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    fun setStringSet(key: PrefKey, value: Set<String?>?) {
        val editor = preferences.edit()
        editor.putStringSet(key.name, value)
        editor.apply()
    }

    fun getStringSet(key: PrefKey): Set<String>? {
        return preferences.getStringSet(key.name, HashSet())
    }

    fun setString(key: PrefKey, value: String?) {
        val editor = preferences.edit()
        editor.putString(key.name, value)
        editor.apply()
    }

    fun getString(key: PrefKey): String? {
        return preferences.getString(key.name, "")
    }

    fun getString(key: PrefKey, defaultValue: String?): String? {
        return preferences.getString(key.name, defaultValue)
    }

    fun setFloat(key: PrefKey, value: Float) {
        val editor = preferences.edit()
        editor.putFloat(key.name, value)
        editor.apply()
    }

    fun getFloat(key: PrefKey): Float {
        return preferences.getFloat(key.name, 0f)
    }

    fun setLong(key: PrefKey, value: Long) {
        val editor = preferences.edit()
        editor.putLong(key.name, value)
        editor.apply()
    }

    fun getLong(key: PrefKey): Long {
        return preferences.getLong(key.name, 0)
    }

    fun getLong(key: PrefKey, def: Long): Long {
        return preferences.getLong(key.name, def)
    }

    fun setDouble(key: PrefKey, value: Double) {
        val editor = preferences.edit()
        editor.putLong(key.name, java.lang.Double.doubleToRawLongBits(value))
        editor.apply()
    }

    fun getDouble(key: PrefKey): Double {
        return java.lang.Double.longBitsToDouble(preferences.getLong(key.name, 0))
    }

    fun setInt(key: PrefKey, value: Int) {
        val editor = preferences.edit()
        editor.putInt(key.name, value)
        editor.apply()
    }

    fun getInt(key: PrefKey): Int {
        return preferences.getInt(key.name, 0)
    }

    fun getInt(key: PrefKey, defValue: Int): Int {
        return preferences.getInt(key.name, defValue)
    }

    fun setBoolean(key: PrefKey, value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(key.name, value)
        editor.apply()
    }

    fun getBoolean(key: PrefKey): Boolean {
        return preferences.getBoolean(key.name, false)
    }

    fun getBoolean(key: PrefKey, defVal: Boolean): Boolean {
        return preferences.getBoolean(key.name, defVal)
    }

    fun deleteByPref(key: PrefKey) {
        val editor = preferences.edit()
        editor.remove(key.name)
        editor.apply()
    }

    fun deleteAll() {
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }

}

sealed class PrefKey(val name: String) {
    object LOGIN_TOKEN : PrefKey("LOGIN_TOKEN".lowercase())
    object LOGIN_NAME : PrefKey("LOGIN_USERNAME".lowercase())
    object LOGIN_USERID : PrefKey("LOGIN_USERID".lowercase())
    object LOGIN_FLAG : PrefKey("LOGIN_FLAG".lowercase())
}