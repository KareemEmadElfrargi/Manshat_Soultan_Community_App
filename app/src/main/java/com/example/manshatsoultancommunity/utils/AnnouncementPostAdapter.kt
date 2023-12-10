package com.example.manshatsoultancommunity.utils

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.Resource
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.SportPostAdapter
import com.example.manshatsoultancommunity.databinding.ItemApppintmentMatchsBinding
import com.example.manshatsoultancommunity.databinding.ItemPostAnnouncementBinding
import com.example.manshatsoultancommunity.databinding.ItemPostTextBinding
import com.example.manshatsoultancommunity.features.news.data.model.AnnouncementPost
import com.example.manshatsoultancommunity.features.news.data.model.AppointmentMatch
import com.example.manshatsoultancommunity.features.news.data.model.PostCaption
import com.google.android.material.resources.MaterialResources.getDimensionPixelSize
import com.google.api.ResourceProto.resource

@Suppress("UNREACHABLE_CODE")
class AnnouncementPostAdapter(
    private var listOfPostAnnouncement:List<AnnouncementPost>,
    val context :Context):RecyclerView.Adapter<AnnouncementPostAdapter.BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            ANNOUNCEMENT_POST_VIEW_HOLDER -> {
                val view = inflater.inflate(R.layout.item_post_announcement,parent,false)
                AnnouncementImageViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
        return super.createViewHolder(parent,viewType)
    }
    override fun getItemCount() = listOfPostAnnouncement.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when(holder){
            is AnnouncementImageViewHolder ->{
                if (position < listOfPostAnnouncement.size){
                    val currentPost = listOfPostAnnouncement[position]
                    holder.binding.apply{
                        AnnouncementTitle.text = currentPost.titleAnnouncement
                        AnnouncementDescription.text = currentPost.descriptionAnnouncement
                        AnnouncementDate.text = currentPost.datePostedAnnouncement
                        currentPost?.imageAnnouncement?.let { imageViewAnnouncement.setImageResource(it) }
                        if (currentPost.statusNew){
                            iconImageView.visibility = View.VISIBLE
                        }else{
                            iconImageView.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
            0 -> ANNOUNCEMENT_POST_VIEW_HOLDER
            else -> ANNOUNCEMENT_POST_VIEW_HOLDER
        }
    }
    abstract class BaseViewHolder(view:View):RecyclerView.ViewHolder(view)
    class AnnouncementImageViewHolder(view: View) : BaseViewHolder(view){
        val binding = ItemPostAnnouncementBinding.bind(view)
    }
    companion object{
        const val ANNOUNCEMENT_POST_VIEW_HOLDER = 10;
    }

}