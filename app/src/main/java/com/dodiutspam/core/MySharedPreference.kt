package com.dodiutspam.core

import android.content.Context
import android.content.SharedPreferences

class MySharedPreference(context: Context) {
    private val sharedPreferences : SharedPreferences = context.getSharedPreferences("dodi", Context.MODE_PRIVATE)

    fun saveRegister(username: String, password: String){
        val editor = sharedPreferences.edit()
        editor.putString("user", username)
        editor.putString("pass", password)
        editor.apply()
    }
    fun saveIsLogin(status: Boolean){
        val editor = sharedPreferences.edit()
        editor.putBoolean("islogin", status)
        editor.apply()
    }
    fun getIsLogin(): Boolean?{
        return sharedPreferences.getBoolean("islogin", false)
    }
    fun getUser(): String?{
        return sharedPreferences.getString("user", null)
    }
    fun getPass(): String?{
        return sharedPreferences.getString("pass", null)
    }
}