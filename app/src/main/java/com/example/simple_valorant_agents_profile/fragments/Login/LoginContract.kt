package com.example.simple_valorant_agents_profile.fragments.Login

import android.app.Activity
import android.content.Context
import android.content.Intent

interface LoginContract {

    interface View {

        fun getContext(mainActivity: Activity): Context

        fun onLoading()

        fun offLoading()

        fun getUsername() : String

        fun getPassword() : String
    }

    interface Model {

        fun verifyCreds(intent: Intent, context: Context, username: String, password: String)

    }

    interface Presenter {

        fun loginButtonOnClick()

    }
}