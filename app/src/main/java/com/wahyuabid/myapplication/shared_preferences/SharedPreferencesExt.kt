package com.wahyuabid.myapplication.shared_preferences

import android.content.SharedPreferences

/**
 * Created by W Abid 26/09/20.
 * github.com/wahyuabied
 * wahyu.abied@gmail.com
 *
 */

@Suppress("UNCHECKED_CAST")
fun SharedPreferences.update(vararg values: Pair<String, Any>) {
    val editor = this.edit()
    values.forEach {
        when (it.second) {
            is Int -> editor.putInt(it.first, it.second as Int)
            is Boolean -> editor.putBoolean(it.first, it.second as Boolean)
            is Float -> editor.putFloat(it.first, it.second as Float)
            is Long -> editor.putLong(it.first, it.second as Long)
            is String -> editor.putString(it.first, it.second as String)
            is MutableSet<*> -> editor.putStringSet(it.first, it.second as Set<String>)
        }
    }
    editor.apply()
}

fun SharedPreferences.removes(vararg keys: String) {
    val editor = this.edit()
    keys.forEach {
        editor.remove(it)
    }
    editor.apply()
}
