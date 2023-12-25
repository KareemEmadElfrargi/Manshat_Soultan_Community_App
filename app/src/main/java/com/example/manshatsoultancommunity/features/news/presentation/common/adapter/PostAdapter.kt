package com.example.manshatsoultancommunity.features.news.presentation.common.adapter

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.ItemPostBinding
import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.util.Constants.ADMIN_NAME_KEY
import com.example.manshatsoultancommunity.util.SharedPreferencesManager
import com.example.manshatsoultancommunity.util.changeColorOfVectorIcon
import com.example.manshatsoultancommunity.util.getAdminData
import com.example.manshatsoultancommunity.util.visibilityGone
import com.example.manshatsoultancommunity.util.visibilityVisible

class PostAdapter(listOfPost:List<Post>, val context :Context,
private val listener:InteractionWithOptionPost?):RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
   private val reversedList = listOfPost.reversed()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false)
        return PostViewHolder(view)
    }
    val username = SharedPreferencesManager(context).getString(ADMIN_NAME_KEY)
    override fun getItemCount() =  reversedList.size
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = reversedList[position]
        holder.binding.apply {
            profileName.text = currentPost.nameOfCategory
            authorName.text = currentPost.author
            postCaption.text = currentPost.content
            postTime.text = currentPost.timeOfPost



            Glide.with(context).load(currentPost.imageOfChannel).into(profileImage)

            if (!currentPost.imageOfPost.isNullOrBlank()){
                Glide.with(context).load(currentPost.imageOfPost).into(postImage)
                postImage.setOnLongClickListener {
                    listener?.onLongPress(currentPost,it)
                    true
                }
            }else{
                cardForImage.visibilityGone()
            }

            if(username == currentPost.author){
                menuOptions.apply {
                    visibilityVisible()
                    setOnClickListener {
                        listener?.onClickOptionPopupMenu(currentPost,menuOptions)
                        changeColorOfVectorIcon(context,R.drawable.circle_menu_3,R.color.title)
                    }
                }

            }else{
                menuOptions.visibilityGone()
            }
            when(currentPost.categoryType){
                "Rip" -> {
                    //
                }
            }

            when (currentPost.authorRating){
                1,2 ->{
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