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

class RegisterActivity: ComponentActivity() {
    private lateinit var mysharedpreference : MySharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_page)
        mysharedpreference = MySharedPreference(this)
        val goBack : TextView = findViewById(R.id.register_goback)
        val toLogin : TextView = findViewById(R.id.register_tologin)
        val registerUser : EditText = findViewById(R.id.register_user)
        val registerPass : EditText = findViewById(R.id.register_pass)
        val registerButton : Button = findViewById(R.id.register_button)
        goBack.setOnClickListener {
            val i = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }
        toLogin.setOnClickListener {
            val i = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
        registerButton.setOnClickListener {
            if(registerUser.text.toString().isEmpty() || registerPass.text.toString().isEmpty()){
                Toast.makeText(this, "Please input username and passowrd", Toast.LENGTH_SHORT).show()
            }
            else{
                mysharedpreference.saveRegister(registerUser.text.toString(), registerPass.text.toString())
                Toast.makeText(this, "Successfully Registered!", Toast.LENGTH_SHORT).show()
                val i = Intent(this@RegisterActivity, DashboardActivity::class.java)
                startActivity(i)
                finish()
            }
        }
    }
    override fun onResume() {
        super.onResume()
        if(mysharedpreference.getIsLogin() == true){
            Toast.makeText(this, "Already Login", Toast.LENGTH_SHORT).show()
            val i = Intent(this@RegisterActivity, DashboardActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}