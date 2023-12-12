package com.example.manshatsoultancommunity.features.advertisement.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.manshatsoultancommunity.databinding.FragmentAdsBinding
import com.example.manshatsoultancommunity.features.advertisement.data.model.AnnouncementPost
import com.example.manshatsoultancommunity.features.advertisement.presentation.common.ViewModels.AnnouncementPostViewModel
import com.example.manshatsoultancommunity.features.advertisement.presentation.common.adapter.AnnouncementPostAdapter
import com.example.manshatsoultancommunity.utils.Constants.CHILD_OF_ADS_REALTIME
import com.example.manshatsoultancommunity.utils.Resource
import com.example.manshatsoultancommunity.utils.showToast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AdsFragment : Fragment() {

    private lateinit var binding: FragmentAdsBinding
    private lateinit var announcementAdapter: AnnouncementPostAdapter
    private lateinit var firebaseDatabase: FirebaseDatabase
    private val viewModel: AnnouncementPostViewModel by viewModels()
    private var valueEventListenerAdsPost: ValueEventListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launch {
            viewModel.adsPostList.collectLatest { result ->
                when (result) {
                    is Resource.Loading -> showLoading()
                    is Resource.Success -> {
                        hideLoading()
                        setUpAdsRecycleView(result.data)
                    }
                    is Resource.Error -> {
                        hideLoading()
                        showToast(result.message.toString())
                    }
                    is Resource.Unspecified -> Unit
                }
            }
        }

        updatedData()
    }

    private fun updatedData() {
        valueEventListenerAdsPost = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listOfPosts = mutableListOf<AnnouncementPost>()
                for (postSnapshot in snapshot.children) {
                    val post = postSnapshot.getValue(AnnouncementPost::class.java)
                    post?.let { listOfPosts.add(post) }
                }
                setUpAdsRecycleView(listOfPosts)
            }

            override fun onCancelled(error: DatabaseError) {}

        }
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDatabase.reference.child(CHILD_OF_ADS_REALTIME)
            .addValueEventListener(valueEventListenerAdsPost!!)
    }

    private fun setUpAdsRecycleView(listOFAds: List<AnnouncementPost>?) {
        val comparator = compareByDescending<AnnouncementPost> { it.statusNew }
        val sortedList = listOFAds?.sortedWith(comparator)
        sortedList?.let {
            if (listOFAds.isNotEmpty()) {
                announcementAdapter = AnnouncementPostAdapter(sortedList, requireContext())
                binding.recyclerViewAnnouncementPage.adapter = announcementAdapter
            } else {

            }
        }
    }

    private fun showLoading() {
        binding.progressBarAdsFragment.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBarAdsFragment.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        firebaseDatabase.reference.child(CHILD_OF_ADS_REALTIME).removeEventListener(valueEventListenerAdsPost!!)
    }
}
