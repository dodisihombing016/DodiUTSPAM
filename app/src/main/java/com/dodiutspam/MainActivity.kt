package com.dodiutspam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.dodiutspam.core.MySharedPreference
import com.dodiutspam.pages.DashboardActivity
import com.dodiutspam.pages.LoginActivity
import com.dodiutspam.pages.RegisterActivity
import com.dodiutspam.ui.theme.DodiUTSPAMTheme

class MainActivity : ComponentActivity() {
    private lateinit var mysharedpreference : MySharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_page)
        mysharedpreference = MySharedPreference(this)
        val toLogin : Button = findViewById(R.id.to_login)
        val toRegister : Button = findViewById(R.id.to_register)
        toLogin.setOnClickListener {
            val i = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
        toRegister.setOnClickListener {
            val i = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(i)
            finish()
        }
    }
    override fun onResume() {
        super.onResume()
        if(mysharedpreference.getIsLogin() == true){
            Toast.makeText(this, "Already Login", Toast.LENGTH_SHORT).show()
            val i = Intent(this@MainActivity, DashboardActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}
