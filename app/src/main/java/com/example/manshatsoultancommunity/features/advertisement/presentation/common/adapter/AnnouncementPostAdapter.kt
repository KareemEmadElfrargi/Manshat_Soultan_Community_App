package com.example.manshatsoultancommunity.features.advertisement.presentation.common.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.ItemPostAnnouncementBinding
import com.example.manshatsoultancommunity.features.advertisement.data.model.AnnouncementPost

@Suppress("UNREACHABLE_CODE")
class AnnouncementPostAdapter(
    private var listOfPostAnnouncement:List<AnnouncementPost>,
    val context :Context,
    private val listenerClickAds : InteractionWithAds
    ):RecyclerView.Adapter<AnnouncementPostAdapter.BaseViewHolder>() {
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
                        dateOfPublisher.text = currentPost.datePostedAnnouncement
                        Glide.with(context).load(currentPost.imageAnnouncement)
                            .into(imageViewAnnouncement)

                        if (currentPost.statusNew!!){
                            iconImageView.visibility = View.VISIBLE
                        }else{
                            iconImageView.visibility = View.GONE
                        }
                        cardViewAds.setOnClickListener {
                            listenerClickAds.onClickOnCardOfAds(currentPost)
                        }

                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataUpdated(newList:List<AnnouncementPost>){
        listOfPostAnnouncement = newList
        notifyDataSetChanged()
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