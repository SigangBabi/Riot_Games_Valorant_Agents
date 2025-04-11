package com.example.simple_valorant_agents_profile.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.simple_valorant_agents_profile.R
import com.example.simple_valorant_agents_profile.fragments.AccountFragment
import com.example.simple_valorant_agents_profile.fragments.GamesFragment
import com.example.simple_valorant_agents_profile.fragments.Home.HomeFragment
import com.example.simple_valorant_agents_profile.fragments.NewsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val scale: Float = applicationContext.resources.displayMetrics.density

        val bottomNav : BottomNavigationView =findViewById(R.id.bottomNavBar)
        val homeFragment = HomeFragment(scale)
        val newsFragment = NewsFragment()
        val accountFragment = AccountFragment()
        val gameFragment = GamesFragment()

        setCurrentFragment(homeFragment)

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.account -> setCurrentFragment(accountFragment)
                R.id.news -> setCurrentFragment(newsFragment)
                R.id.games -> setCurrentFragment(gameFragment)
            }
            true
        }
    }

    private fun setCurrentFragment (fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.fragmentContainer, fragment)
        commit()
    }

}