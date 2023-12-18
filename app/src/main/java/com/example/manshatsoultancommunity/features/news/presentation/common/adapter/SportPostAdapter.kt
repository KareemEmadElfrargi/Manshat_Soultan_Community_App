package com.example.manshatsoultancommunity.features.news.presentation.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.ItemApppintmentMatchsBinding
import com.example.manshatsoultancommunity.databinding.ItemPostBinding
import com.example.manshatsoultancommunity.features.news.data.model.AppointmentMatch
import com.example.manshatsoultancommunity.features.news.data.model.Post

@Suppress("UNREACHABLE_CODE")
class SportPostAdapter(
    private var listOfPost:List<Post>,
    private var listOfAppointmentMatch:List<AppointmentMatch>,
    val context :Context):RecyclerView.Adapter<SportPostAdapter.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            POST_VIEW_HOLDER -> {
                val view = inflater.inflate(R.layout.item_post,parent,false)
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

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        //val currentPost = listOfPost[position]
        //val currentMatch = listOfAppointmentMatch
        when(holder){
            is PostViewHolder -> {
                if (position < listOfPost.size) {
                    val currentPost = listOfPost[position]
                    holder.binding.apply {
                        profileName.text = currentPost.nameOfCategory
                        postTime.text = currentPost.timeOfPost
                        postCaption.text = currentPost.content
                        currentPost.imageOfChannel?.let { profileImage.setImageResource(it) }
                    }
                }
            }
            is AppointmentMatchPostViewHolder -> {
                if (position < listOfAppointmentMatch.size) {
                    val currentMatch = listOfAppointmentMatch[position]
                    holder.binding.apply {
                        team1TextView.text = currentMatch.firstTeam
                        team2TextView.text = currentMatch.secondTeam
                        timeTextView.text = currentMatch.timeOfMatch
                        dayTextView.text = currentMatch.dayOfMatch
                    }
                }
            }
        }
    }

    // position representing the position of the item within the adapter
    override fun getItemViewType(position: Int): Int {
        return when {
            position < listOfAppointmentMatch.size -> APPOINTMENT_MATCH_POST_VIEW_HOLDER
            else -> POST_VIEW_HOLDER
        }
    }
    override fun getItemCount(): Int = maxOf(listOfPost.size, listOfAppointmentMatch.size)
    abstract class BaseViewHolder(view:View):RecyclerView.ViewHolder(view)
    class PostViewHolder(view: View) : BaseViewHolder(view){
        val binding = ItemPostBinding.bind(view)
    }
    class AppointmentMatchPostViewHolder(view: View) : BaseViewHolder(view){
        val binding = ItemApppintmentMatchsBinding.bind(view)
    }
    companion object{
        const val POST_VIEW_HOLDER = 10;
        const val APPOINTMENT_MATCH_POST_VIEW_HOLDER = 20;
    }

}