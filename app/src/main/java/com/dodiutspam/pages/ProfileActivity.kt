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

class ProfileActivity: ComponentActivity() {
    private lateinit var mysharedpreference : MySharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)
        mysharedpreference = MySharedPreference(this)
        val toDashboard: Button = findViewById(R.id.profile_todashboard)
        val logout : Button = findViewById(R.id.logout)
        val getNama = getString(R.string.nama)
        val getNim = getString(R.string.nim)
        val tampilNama : TextView = findViewById(R.id.tampilnama)
        val tampilNim : TextView = findViewById(R.id.tampilnim)
        tampilNama.text = "Nama: $getNama"
        tampilNim.text = "NIM: $getNim"
        toDashboard.setOnClickListener {
            val i = Intent(this@ProfileActivity, DashboardActivity::class.java)
            startActivity(i)
        }
        logout.setOnClickListener {
            mysharedpreference.saveIsLogin(false)
            Toast.makeText(this, "Logout Success", Toast.LENGTH_SHORT).show()
            val i = Intent(this@ProfileActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}