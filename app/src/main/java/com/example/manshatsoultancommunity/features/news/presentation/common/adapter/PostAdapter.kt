package com.example.manshatsoultancommunity.features.news.presentation.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.ItemPostBinding
import com.example.manshatsoultancommunity.features.news.data.model.Post

class PostAdapter(private var listOfPost:List<Post>, val context :Context):RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false)
        return PostViewHolder(view)
    }
    override fun getItemCount() =  listOfPost.size
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = listOfPost[position]
        holder.binding.apply {
            profileName.text = currentPost.nameOfCategory
            authorName.text = currentPost.author
            postCaption.text = currentPost.content
            Glide.with(context).load(currentPost.imageOfChannel).into(profileImage)
            if (currentPost.imageOfPost.isNullOrBlank()){
                Glide.with(context).load(currentPost.imageOfPost).into(postImage)
            }
        }
    }
    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemPostBinding.bind(view)
    }
}