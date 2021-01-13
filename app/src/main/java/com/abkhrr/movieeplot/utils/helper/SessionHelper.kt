package com.abkhrr.movieeplot.utils.helper

import android.content.Context
import com.abkhrr.movieeplot.utils.constant.AppConstant

class SessionHelper(context: Context) {

    private val prefs = context.getSharedPreferences(AppConstant.APP_SESSION, Context.MODE_PRIVATE)

    fun setUsername(username: String, session: Boolean){
        val editor = prefs.edit()
        editor.putString(AppConstant.USERNAME, username)
        editor.putBoolean(AppConstant.HAS_SESSION, session)
        editor.apply()
    }

    fun getUsername(): String{
        return prefs.getString(AppConstant.USERNAME, "Username") ?: ""
    }

    fun hasSession(): Boolean{
        return prefs.getBoolean(AppConstant.HAS_SESSION, false)
    }

    fun endSession(){
        prefs.edit().clear().apply()
    }

}