package com.dodiutspam.pages

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dodiutspam.MainActivity
import com.dodiutspam.R
import com.dodiutspam.core.ApiService
import com.dodiutspam.core.MySharedPreference
import com.dodiutspam.core.UserAdapter
import com.dodiutspam.core.UserResponse
import com.dodiutspam.core.itemViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DashboardActivity: ComponentActivity() {
    private lateinit var mysharedpreference: MySharedPreference
    private lateinit var userlist : ArrayList<itemViewModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)
        val toProfile : Button = findViewById(R.id.dashboard_toprofile)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/") // Ganti URL dengan URL API yang sesuai
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        toProfile.setOnClickListener {
            val i = Intent(this@DashboardActivity, ProfileActivity::class.java)
            startActivity(i)
        }
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val call = apiService.getUsers()
        userlist = ArrayList<itemViewModel>()
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    val users = userResponse?.data
                    if (users != null) {
                        for(i in 0 until users.size){
                            userlist.add(itemViewModel(users[i].first_name.toString(), users[i].id.toString()))
                        }
                        val adapter = UserAdapter(userlist)
                        adapter.notifyDataSetChanged()
                        recyclerview.adapter = adapter
                    }
                    Log.d("API SUCCESS", users.toString())
                    // Lakukan sesuatu dengan data yang diterima (users)
                } else {
                    Log.d("API FAIL", "API DIDNT SUCCESS")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                // Tangani kesalahan jaringan
            }
        })
    }

}