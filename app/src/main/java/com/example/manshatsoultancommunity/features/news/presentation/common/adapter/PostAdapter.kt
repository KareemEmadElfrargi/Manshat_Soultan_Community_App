package com.example.manshatsoultancommunity.features.news.presentation.common.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.ItemPostBinding
import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.util.Constants.ADMIN_NAME_KEY
import com.example.manshatsoultancommunity.util.SharedPreferencesManager
import com.example.manshatsoultancommunity.util.getAdminData
import com.example.manshatsoultancommunity.util.visibilityGone
import com.example.manshatsoultancommunity.util.visibilityVisible

class PostAdapter(private var listOfPost:List<Post>, val context :Context):RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false)
        return PostViewHolder(view)
    }
    val username = SharedPreferencesManager(context).getString(ADMIN_NAME_KEY)
    override fun getItemCount() =  listOfPost.size
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = listOfPost[position]
        holder.binding.apply {
            profileName.text = currentPost.nameOfCategory
            authorName.text = currentPost.author
            postCaption.text = currentPost.content
            postTime.text = currentPost.timeOfPost

            Glide.with(context).load(currentPost.imageOfChannel).into(profileImage)

            if (!currentPost.imageOfPost.isNullOrBlank()){
                Glide.with(context).load(currentPost.imageOfPost).into(postImage)
            }else{
                cardForImage.visibilityGone()
            }

            if(username == currentPost.author){
                deleteIcon.visibilityVisible()
                editIcon.visibilityVisible()
            }else{
                deleteIcon.visibilityGone()
                editIcon.visibilityGone()
            }

            when (currentPost.authorRating){
                1 -> {
                    val newDrawableStart: Drawable? = ContextCompat.getDrawable(context, R.drawable.admin_ic_n)
                    authorName.setCompoundDrawablesWithIntrinsicBounds(null,null,newDrawableStart,null)
                }
                2 ->{
                    val newDrawableStart: Drawable? = ContextCompat.getDrawable(context, R.drawable.auth_admin_icon_n)
                    authorName.setCompoundDrawablesWithIntrinsicBounds(null,null,newDrawableStart,null)
                }
                3 ->{
                    val newDrawableStart: Drawable? = ContextCompat.getDrawable(context, R.drawable.auth_icon_n)
                    authorName.setCompoundDrawablesWithIntrinsicBounds(null,null,newDrawableStart,null)
                }
            }
        }
    }
    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemPostBinding.bind(view)
    }
}