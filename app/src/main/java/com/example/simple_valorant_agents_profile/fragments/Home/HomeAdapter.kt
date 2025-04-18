package com.example.simple_valorant_agents_profile.fragments.Home

import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.simple_valorant_agents_profile.R

class HomeAdapter(
    private val context: Context,
    private val images: List<String>,
    private val agentName: List<String>,
    private val agentRole: List<String>,
    private val roleIcons: List<String>,
    private val agentIcons: List<String>,
    private val frontAnimation: AnimatorSet,
    private val backAnimation: AnimatorSet,
    private val scale: Float
): BaseAdapter() {

    private var isFront: Boolean = true

    override fun getCount(): Int = images.size

    override fun getItem(position: Int): Any = images[position]

    override fun getItemId(position: Int): Long = position.toLong()

    @SuppressLint("SuspiciousIndentation")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        Log.w("bora", images.toString() )
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.home_grid_view_items, parent, false)
        val imageView = view.findViewById<ImageView>(R.id.agentCard)
        val agentNames = view.findViewById<TextView>(R.id.agentName)
        val agentRoles = view.findViewById<TextView>(R.id.agentRole)
        val roleIcon = view.findViewById<ImageView>(R.id.roleIcon)
        val agentIcon = view.findViewById<ImageButton>(R.id.profileImage)
        val imageDescription = view.findViewById<FrameLayout>(R.id.agentInformation)
        imageView.cameraDistance = 8000 * scale
        imageDescription.cameraDistance = 8000 * scale

        Glide.with(context)
            .load(images[position])
            .centerCrop()
            .into(imageView)

        Glide.with(context)
            .load(roleIcons[position])
            .centerCrop()
            .into(roleIcon)

        Glide.with(context)
            .load(agentIcons[position])
            .centerCrop()
            .into(agentIcon)

        view.setOnClickListener {
            if (isFront) {
                frontAnimation.setTarget(imageView)
                backAnimation.setTarget(imageDescription)
                frontAnimation.start()
                backAnimation.start()
                isFront = false
            } else{
                frontAnimation.setTarget(imageDescription)
                backAnimation.setTarget(imageView)
                backAnimation.start()
                frontAnimation.start()
                isFront = true
            }
        }

        agentNames.text = agentName[position]
        agentRoles.text = agentRole[position]

        return view
    }
}