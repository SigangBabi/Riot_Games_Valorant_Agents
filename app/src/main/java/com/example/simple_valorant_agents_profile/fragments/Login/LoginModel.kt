package com.example.simple_valorant_agents_profile.fragments.Login

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.simple_valorant_agents_profile.api.LoginCredentials.LoginCredsItem
import com.example.simple_valorant_agents_profile.api.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginModel (private val view: LoginContract.View) : LoginContract.Model {

    val baseUrl = "https://mocki.io/"
    val tag = "CHECK_RESPONSE"

    private val apiService = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiServices::class.java)

    override fun verifyCreds(intent: Intent, context: Context, username: String, password: String) {
        val call = apiService.getLoginCreds()

        call.enqueue(object : Callback<List<LoginCredsItem>> {
            override fun onResponse(
                call: Call<List<LoginCredsItem>>,
                response: Response<List<LoginCredsItem>>
            ) {
                if (response.isSuccessful){
                    val loginCredentials = response.body()
                    val checkLoginCreds = loginCredentials?.find { it.username == username && it.password == password }
                    if (checkLoginCreds != null){
                        Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show()
                        context.startActivity(intent)
                        view.offLoading()
                    }else{
                        Toast.makeText(context, "Invalid Login Credentials!", Toast.LENGTH_LONG).show()
                        view.offLoading()
                    }
                }
            }

            override fun onFailure(call: Call<List<LoginCredsItem>>, t: Throwable) {
                Log.i(tag, "onFailure: ${t.message}")
            }
        })
    }
}