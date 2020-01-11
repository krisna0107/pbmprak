package com.krisna.ngeengg

import android.content.Context
import android.content.SharedPreferences

class setLogin(ctx: Context) {
    //val EXTRA_CLEAR_CREDENTIALS = "com.auth0.CLEAR_CREDENTIALS"
    val EXTRA_ACCESS_TOKEN = "com.auth0.ACCESS_TOKEN"

    val token = ctx.getSharedPreferences(EXTRA_ACCESS_TOKEN, Context.MODE_PRIVATE)
    //val credntial = ctx.getSharedPreferences(EXTRA_CLEAR_CREDENTIALS, Context.MODE_PRIVATE)

    fun getVal(KEY_NAME: String): String? = token.getString(KEY_NAME,"Auth")
    //fun getValClear(KEY_NAME: String): String? = credntial.getString(KEY_NAME,"logout")

    fun save(KEY_NAME: String, value: String?){
        val editor: SharedPreferences.Editor = token.edit()
        editor.putString(KEY_NAME, value)
        editor.apply()
    }

//    fun saveClear(KEY_NAME: String, value: String?){
//        val editor: SharedPreferences.Editor = credntial.edit()
//        editor.putString(KEY_NAME, value)
//        editor.apply()
//    }

//    fun save(KEY_NAME: String, value: Boolean){
//        val editor: SharedPreferences.Editor = credntial.edit()
//        editor.putBoolean(KEY_NAME, value)
//        editor.apply()
//    }
}