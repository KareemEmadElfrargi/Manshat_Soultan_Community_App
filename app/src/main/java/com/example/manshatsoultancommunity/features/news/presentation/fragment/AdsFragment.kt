package com.example.manshatsoultancommunity.features.news.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.FragmentAdsBinding
import com.example.manshatsoultancommunity.features.advertisement.data.model.AnnouncementPost
import com.example.manshatsoultancommunity.features.advertisement.presentation.common.adapter.AnnouncementPostAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class AdsFragment: Fragment() {
    private lateinit var binding : FragmentAdsBinding
    private lateinit var announcementAdapter : AnnouncementPostAdapter
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
        val titleOFAnnouncement = "إعلان إفتتاح عيادة جديدة"
        val imageOFAnnouncement = R.drawable.imag_clinic
        val statusNew = false
        val contentOfAnnouncement = "يعلن الدكتور محمد ياسر عن فتح عيادة جديدة في شارع المحطة"
        val dateOfAnnouncement = "Mon , 10:23"

        val obj = AnnouncementPost(titleOFAnnouncement,imageOFAnnouncement,contentOfAnnouncement,dateOfAnnouncement,statusNew)
        val obj2 = AnnouncementPost(titleOFAnnouncement,imageOFAnnouncement,contentOfAnnouncement,dateOfAnnouncement)
        val listOFAds = listOf<AnnouncementPost>(obj,obj2)
        announcementAdapter = AnnouncementPostAdapter(listOFAds,requireContext())
        binding.recyclerViewAnnouncementPage.adapter = announcementAdapter
    }
}