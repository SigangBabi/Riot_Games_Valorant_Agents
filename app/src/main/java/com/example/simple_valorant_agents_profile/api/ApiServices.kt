package com.example.simple_valorant_agents_profile.api

import com.example.simple_valorant_agents_profile.api.AgentsInfo.AgentsInformationItem
import com.example.simple_valorant_agents_profile.api.LoginCredentials.LoginCredsItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("v1/bb39c5a5-f5c4-4bf8-b038-6dd042b4cbe1")
    fun getLoginCreds() : Call<List<LoginCredsItem>>

    @GET("v1/14385927-8ace-48cf-97de-43d7491ebc03")
    fun getAgentInfo() : Call<List<AgentsInformationItem>>

}