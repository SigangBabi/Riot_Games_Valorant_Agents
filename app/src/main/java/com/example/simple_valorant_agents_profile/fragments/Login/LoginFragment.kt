package com.example.simple_valorant_agents_profile.fragments.Login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import com.example.simple_valorant_agents_profile.R
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment(), LoginContract.View {

    private lateinit var presenter: LoginPresenter
    lateinit var inputUsername : TextInputEditText
    lateinit var inputPassword : TextInputEditText
    lateinit var progressBar : ProgressBar

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val loginBtn = view.findViewById<Button>(R.id.login_btn)
        inputUsername = view.findViewById(R.id.inputUsername)
        inputPassword = view.findViewById(R.id.inputPassword)
        progressBar = view.findViewById(R.id.progressBar)

        presenter = LoginPresenter(this, LoginModel(this))

        loginBtn.setOnClickListener {
            presenter.loginButtonOnClick()
        }


        return view
    }

    override fun getContext(mainActivity: Activity): Context {
        return requireContext()
    }

    override fun onLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun offLoading() {
        progressBar.visibility = View.GONE
    }

    override fun getUsername(): String {
        return inputUsername.text.toString()
    }

    override fun getPassword(): String {
        return inputPassword.text.toString()
    }

}