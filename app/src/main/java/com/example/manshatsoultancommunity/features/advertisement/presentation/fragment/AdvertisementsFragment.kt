package com.example.manshatsoultancommunity.features.advertisement.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.manshatsoultancommunity.databinding.FragmentAdvertisementBinding
import com.example.manshatsoultancommunity.features.advertisement.data.model.Advertisements
import com.example.manshatsoultancommunity.features.advertisement.presentation.common.ViewModels.AdvertisementsViewModel
import com.example.manshatsoultancommunity.features.advertisement.presentation.common.adapter.AdvertisementsAdapter
import com.example.manshatsoultancommunity.features.advertisement.presentation.common.adapter.InteractionWithAdvertisement
import com.example.manshatsoultancommunity.utils.Constants.CHILD_OF_ADS_REALTIME
import com.example.manshatsoultancommunity.utils.Resource
import com.example.manshatsoultancommunity.utils.contactByWhatsApp
import com.example.manshatsoultancommunity.utils.dailogs.setupButtonSheetDetailsDialog
import com.example.manshatsoultancommunity.utils.isInternetAvailable
import com.example.manshatsoultancommunity.utils.showToastStyle
import com.example.manshatsoultancommunity.utils.visibilityGone
import com.example.manshatsoultancommunity.utils.visibilityVisible
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AdvertisementsFragment : Fragment(),InteractionWithAdvertisement {

    private lateinit var binding: FragmentAdvertisementBinding
    private lateinit var announcementAdapter: AdvertisementsAdapter
    private lateinit var firebaseDatabase: FirebaseDatabase
    private val viewModel: AdvertisementsViewModel by viewModels()
    private var valueEventListenerAdsPost: ValueEventListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdvertisementBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseDatabase = FirebaseDatabase.getInstance()

            lifecycleScope.launch {
                viewModel.advertisementsList.collectLatest { result ->
                    when (result) {
                        is Resource.Loading -> {
                            showLoading()
                        }

                        is Resource.Success -> {
                            hideLoading()
                            if (result.data?.size == 0) {
                                showEmptyListUI()
                            } else {
                                hideEmptyListUI() // need to close app to hide!!
                                setUpAdsRecycleView(result.data)
                            }
                        }

                        is Resource.Error -> {
                            hideLoading()
                            if (result.message.toString() == "Error fetching remote data") {
//                                hideEmptyListUI()
//                                showUnconnectedInternetUI()
//                                Log.e("AdvertisementsFragment",result.message.toString())

                            } else if (result.message.toString() == "Empty List cached on your phone!") {
                                showEmptyListUI()
                                connectedInternetUI()
                            }
                        }

                        is Resource.Unspecified -> Unit
                    }
                }
            }

        updatedData()
    }

    override fun onResume() {
        super.onResume()
    }


    private fun updatedData() {
        valueEventListenerAdsPost = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listOfAdvertisements = mutableListOf<Advertisements>()
                for (advertisementSnapshot in snapshot.children) {
                    val post = advertisementSnapshot.getValue(Advertisements::class.java)
                    post?.let { listOfAdvertisements.add(post) }
                }
                setUpAdsRecycleView(listOfAdvertisements)
            }

            override fun onCancelled(error: DatabaseError) {}

        }
        firebaseDatabase.reference.child(CHILD_OF_ADS_REALTIME)
            .addValueEventListener(valueEventListenerAdsPost!!)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpAdsRecycleView(listOfAdvertisements: List<Advertisements>?) {
        listOfAdvertisements?.let {
            announcementAdapter = AdvertisementsAdapter(listOfAdvertisements.reversed(), requireContext(),this)
            announcementAdapter.notifyDataSetChanged()
            binding.recyclerViewAnnouncementPage.adapter = announcementAdapter

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        firebaseDatabase.reference.child(CHILD_OF_ADS_REALTIME).removeEventListener(valueEventListenerAdsPost!!)
    }
    override fun onClickOnCardOfAds(adsPost: Advertisements) {
        setupButtonSheetDetailsDialog(adsPost) { phoneNumber ->
            val countryCode = "+2"
            if (isInternetAvailable()){
                contactByWhatsApp(phoneNumber,countryCode)
            }else {
                showToastStyle("يجب عليك الاتصال بالانترنت للتواصل")
            }
        }
    }


    private fun connectedInternetUI() {
        binding.lostConnectAnimation.visibilityGone()
    }
    private fun hideEmptyListUI() {
        binding.emptyListAnimation.visibilityGone()
    }
    private fun showEmptyListUI() {
        binding.emptyListAnimation.visibilityVisible()
    }
    private fun showUnconnectedInternetUI() {
        binding.lostConnectAnimation.visibilityVisible()
    }
    private fun showLoading() {
        binding.progressBarAdsFragment.visibilityVisible()
    }
    private fun hideLoading() {
        binding.progressBarAdsFragment.visibilityGone()
    }

}
