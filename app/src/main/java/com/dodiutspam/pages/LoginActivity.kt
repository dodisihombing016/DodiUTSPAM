package com.dodiutspam.pages

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.dodiutspam.MainActivity
import com.dodiutspam.R
import com.dodiutspam.core.MySharedPreference
import kotlin.math.log

class LoginActivity: ComponentActivity() {
    private lateinit var mysharedpreference : MySharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)
        mysharedpreference = MySharedPreference(this)
        val goBack : TextView = findViewById(R.id.login_goback)
        val toRegister : TextView = findViewById(R.id.login_toregister)
        val loginUser : EditText = findViewById(R.id.login_user)
        val loginPass : EditText = findViewById(R.id.login_pass)
        val loginButton : Button = findViewById(R.id.login_button)
        goBack.setOnClickListener {
            val i = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }
        toRegister.setOnClickListener {
            val i = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(i)
            finish()
        }
        loginButton.setOnClickListener {
            if(loginUser.text.toString().isEmpty() || loginPass.text.toString().isEmpty()){
                Toast.makeText(this, "Please input username and passowrd", Toast.LENGTH_SHORT).show()
            }
            else{
                if(loginUser.text.toString()==mysharedpreference.getUser() && loginPass.text.toString()==mysharedpreference.getPass()){
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                    mysharedpreference.saveIsLogin(true)
                    val i = Intent(this@LoginActivity, DashboardActivity::class.java)
                    startActivity(i)
                    finish()
                }
                else{
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(mysharedpreference.getIsLogin() == true){
            Toast.makeText(this, "Already Login", Toast.LENGTH_SHORT).show()
            val i = Intent(this@LoginActivity, DashboardActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}