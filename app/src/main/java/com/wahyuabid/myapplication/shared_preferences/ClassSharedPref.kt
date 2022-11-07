package com.wahyuabid.myapplication.shared_preferences

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wahyuabid.myapplication.model.ClassModel


class ClassSharedPref(private val pref: SharedPreferences) {

    companion object {
        const val CLASS = "class"
    }

    fun getClass(): MutableList<ClassModel> {
        try {
            return Gson().fromJson(
                pref.getString(CLASS, ""),
                object : TypeToken<List<ClassModel?>?>() {}.type
            )
        } catch (e: Exception) {
            return mutableListOf()
        }
    }

    fun setClass(listClass: MutableList<ClassModel>){
        pref.update(CLASS to Gson().toJson(listClass))
    }

    fun clearSession() {
        pref.removes(CLASS)
    }

}