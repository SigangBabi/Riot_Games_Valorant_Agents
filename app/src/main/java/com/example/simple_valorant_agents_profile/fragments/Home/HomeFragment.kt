package com.example.simple_valorant_agents_profile.fragments.Home

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.Toast
import com.example.simple_valorant_agents_profile.R
import com.example.simple_valorant_agents_profile.api.AgentsInfo.AgentsInformationItem
import com.example.simple_valorant_agents_profile.api.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.truncate

class HomeFragment(scale : Float) : Fragment() {

    private lateinit var gridView: GridView

    private val imgScale: Float = scale

    val baseUrl = "https://mocki.io/"

    private var imageDisplayed : Boolean = false

    private val imageUrls = mutableListOf<String>()

    private val agentName = mutableListOf<String>()

    private val agentRole = mutableListOf<String>()

    private val roleIconUrls = mutableListOf<String>()

    private val agentIconUrls = mutableListOf<String>()

    lateinit var frontAnimation: AnimatorSet
    lateinit var backAnimation: AnimatorSet
    var isFront : Boolean = true

    private val apiService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiServices::class.java)

    private val getAgentInfo = apiService.getAgentInfo()

    private fun getAgentInformation() = getAgentInfo.enqueue(object : Callback<List<AgentsInformationItem>>{
        override fun onResponse(
            call: Call<List<AgentsInformationItem>>,
            response: Response<List<AgentsInformationItem>>
        ) {
            val agentInformation = response.body()
            var index = 0
            while(agentInformation!!.size > index){
                imageUrls.add(agentInformation!![index].agentImg)
                agentName.add(agentInformation!![index].agent)
                agentRole.add(agentInformation!![index].role)
                roleIconUrls.add(agentInformation!![index].roleLogo)
                agentIconUrls.add(agentInformation!![index].agentLogo)


                index += 1
            }

        }

        override fun onFailure(call: Call<List<AgentsInformationItem>>, t: Throwable) {
            TODO("Not yet implemented")
        }

    })

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val cardView = inflater.inflate(R.layout.home_grid_view_items, container, false)
        gridView = view.findViewById(R.id.agentsView)

        frontAnimation = AnimatorInflater.loadAnimator(requireContext(), R.animator.front_card_animation) as AnimatorSet
        backAnimation = AnimatorInflater.loadAnimator(requireContext(), R.animator.back_card_animation) as AnimatorSet

        if (!imageDisplayed){
            getAgentInformation()
            imageDisplayed = true
        }
        gridView.adapter = HomeAdapter(requireContext(), imageUrls, agentName, agentRole, roleIconUrls, agentIconUrls, frontAnimation, backAnimation, imgScale)
        return view
    }

}