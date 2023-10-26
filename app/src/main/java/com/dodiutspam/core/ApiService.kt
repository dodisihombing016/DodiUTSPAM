package com.dodiutspam.core

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users") // Ganti "users" dengan path sesuai dengan API yang digunakan
    fun getUsers(): Call<UserResponse>
}