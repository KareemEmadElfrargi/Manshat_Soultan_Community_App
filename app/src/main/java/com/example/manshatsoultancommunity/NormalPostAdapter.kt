package com.example.manshatsoultancommunity

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.Resource
import com.example.manshatsoultancommunity.databinding.ItemPostTextBinding
import com.example.manshatsoultancommunity.features.news.data.model.PostCaption
import com.google.android.material.resources.MaterialResources.getDimensionPixelSize
import com.google.api.ResourceProto.resource

class NormalPostAdapter(private var listOfPost:List<PostCaption> , val context :Context):RecyclerView.Adapter<NormalPostAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post_text,parent,false)
        return PostViewHolder(view)
    }
    override fun getItemCount() =  listOfPost.size
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = listOfPost[position]
        holder.binding.apply {
            profileName.text = currentPost.nameOfPublisher
            postTime.text = currentPost.timeOfPost
            postCaption.text = currentPost.content
            currentPost?.imageOfPublisher?.let { profileImage.setImageResource(it) }
            if (currentPost.status){

                val color = ContextCompat.getColor(context, R.color.title)
                profileImage.apply {
                    borderWidth = 10
                    borderColor = color
                }
            }
        }
    }
    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemPostTextBinding.bind(view)
    }
}