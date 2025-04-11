package com.example.simple_valorant_agents_profile.fragments.Login

import android.content.Intent
import com.example.simple_valorant_agents_profile.activities.HomeActivity
import com.example.simple_valorant_agents_profile.activities.main.MainActivity

class LoginPresenter (private val view: LoginContract.View,
                      private val model : LoginContract.Model
): LoginContract.Presenter {
    override fun loginButtonOnClick() {
        view.onLoading()
        val openHomePage = Intent(view.getContext(MainActivity()), HomeActivity::class.java)
        model.verifyCreds(openHomePage, view.getContext(MainActivity()), view.getUsername(), view.getPassword())
    }

}