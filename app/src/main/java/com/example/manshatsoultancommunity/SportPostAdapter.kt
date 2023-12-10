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
import com.example.manshatsoultancommunity.databinding.ItemApppintmentMatchsBinding
import com.example.manshatsoultancommunity.databinding.ItemPostTextBinding
import com.example.manshatsoultancommunity.features.news.data.model.AppointmentMatch
import com.example.manshatsoultancommunity.features.news.data.model.PostCaption
import com.google.android.material.resources.MaterialResources.getDimensionPixelSize
import com.google.api.ResourceProto.resource

@Suppress("UNREACHABLE_CODE")
class SportPostAdapter(
    private var listOfPost:List<PostCaption>,
    private var listOfAppointmentMatch:AppointmentMatch,

    val context :Context):RecyclerView.Adapter<SportPostAdapter.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            POST_VIEW_HOLDER -> {
                val view = inflater.inflate(R.layout.item_post_text,parent,false)
                PostViewHolder(view)
            }

            APPOINTMENT_MATCH_POST_VIEW_HOLDER ->{
                val view = inflater.inflate(R.layout.item_apppintment_matchs,parent,false)
                AppointmentMatchPostViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
        return super.createViewHolder(parent,viewType)
    }
    override fun getItemCount(): Int = listOfPost.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentPost = listOfPost[position]
        val currentMatch = listOfAppointmentMatch
        when(holder){
            is PostViewHolder ->{
                holder.binding.apply {
                    profileName.text = currentPost.nameOfPublisher
                    postTime.text = currentPost.timeOfPost
                    postCaption.text = currentPost.content
                    currentPost.imageOfPublisher?.let { profileImage.setImageResource(it) }
                }
            }
            is AppointmentMatchPostViewHolder ->{
                holder.binding.apply{
                    team1TextView.text = currentMatch.firstTeam
                    team2TextView.text = currentMatch.secondTeam
                    timeTextView.text = currentMatch.timeOfMatch
                    dayTextView.text = currentMatch.dayOfMatch
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> APPOINTMENT_MATCH_POST_VIEW_HOLDER
            else -> POST_VIEW_HOLDER
        }
    }
    abstract class BaseViewHolder(view:View):RecyclerView.ViewHolder(view)
    class PostViewHolder(view: View) : BaseViewHolder(view){
        val binding = ItemPostTextBinding.bind(view)
    }
    class AppointmentMatchPostViewHolder(view: View) : BaseViewHolder(view){
        val binding = ItemApppintmentMatchsBinding.bind(view)
    }
    companion object{
        const val POST_VIEW_HOLDER = 10;
        const val APPOINTMENT_MATCH_POST_VIEW_HOLDER = 20;
    }

}