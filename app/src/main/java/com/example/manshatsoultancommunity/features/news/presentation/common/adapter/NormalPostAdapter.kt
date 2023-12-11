package com.example.manshatsoultancommunity.features.news.presentation.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.ItemPostTextBinding
import com.example.manshatsoultancommunity.features.news.data.model.PostCaption

class NormalPostAdapter(private var listOfPost:List<PostCaption>, val context :Context):RecyclerView.Adapter<NormalPostAdapter.PostViewHolder>() {
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
                val paddingInPx = context.resources.getDimensionPixelSize(R.dimen.your_padding_dimension)
                val borderWidthPx = context.resources.getDimensionPixelSize(R.dimen.your_border_width)
                profileImage.apply {
                    borderWidth = borderWidthPx
                    borderColor = color
                    setPadding(paddingInPx,paddingInPx,paddingInPx,paddingInPx)
                }
            }
        }
    }
    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemPostTextBinding.bind(view)
    }
}