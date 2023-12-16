package com.example.manshatsoultancommunity.features.advertisement.presentation.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.ItemPostAdvertisementBinding
import com.example.manshatsoultancommunity.features.advertisement.data.model.Advertisements

@Suppress("UNREACHABLE_CODE")
class AdvertisementsAdapter(
    private var listOfAdvertisements:List<Advertisements>,
    val context :Context,
    private val listenerClickAdvertisements : InteractionWithAdvertisement
    ):RecyclerView.Adapter<AdvertisementsAdapter.BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            ADVERTISMENTS_POST_VIEW_HOLDER -> {
                val view = inflater.inflate(R.layout.item_post_advertisement,parent,false)
                AdvertisementsImageViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
        return super.createViewHolder(parent,viewType)
    }
    override fun getItemCount() = listOfAdvertisements.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when(holder){
            is AdvertisementsImageViewHolder ->{
                if (position < listOfAdvertisements.size){
                    val currentAdvertisement = listOfAdvertisements[position]
                    holder.binding.apply{
                        AdvertisementTitle.text = currentAdvertisement.titleOfAdvertisement
                        AdvertisementDescription.text = currentAdvertisement.descriptionOfAdvertisement
                        dateOfPublisherAdvertisement.text = currentAdvertisement.datePostedAdvertisement
                        Glide.with(context).load(currentAdvertisement.imageOfAdvertisement)
                            .into(imageViewAdvertisement)

                        if (currentAdvertisement.isPinAdvertisement!!){
                            pinIconAdvertisement.visibility = View.VISIBLE
                        }else{
                            pinIconAdvertisement.visibility = View.GONE
                        }

                        cardViewAdvertisement.setOnClickListener {
                            listenerClickAdvertisements.onClickOnCardOfAds(currentAdvertisement)
                        }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
            0 -> ADVERTISMENTS_POST_VIEW_HOLDER
            else -> ADVERTISMENTS_POST_VIEW_HOLDER
        }
    }
    abstract class BaseViewHolder(view:View):RecyclerView.ViewHolder(view)
    class AdvertisementsImageViewHolder(view: View) : BaseViewHolder(view){
        val binding = ItemPostAdvertisementBinding.bind(view)
    }
    companion object{
        const val ADVERTISMENTS_POST_VIEW_HOLDER = 10;
    }

}