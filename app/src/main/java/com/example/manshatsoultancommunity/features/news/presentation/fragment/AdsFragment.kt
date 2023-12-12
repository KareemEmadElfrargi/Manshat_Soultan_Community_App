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
//        val titleOFAnnouncement = "إعلان إفتتاح عيادة جديدة"
//        val titleOFAnnouncement2 = "عروض من Vipers Gym"
//        val imageOFAnnouncement = R.drawable.imag_clinic
//        val imageOFAnnouncement3 = R.drawable.gym_imag
//        val contentOfAnnouncement = "يعلن الدكتور محمد ياسر عن فتح عيادة جديدة في شارع المحطة"
//        val contentOfAnnouncement2 = "يعلن الدكتور فتحي بحيري عن فتح عيادة نسا وتوليد عند شارع المدارس"
//        val contentOfAnnouncement3 = "تم تخفيض الحصه لجميع الطلبه فى جميع المراحل السنيه حتى مرحلة الثانويه ل ١٠ جنيه بدل ١٥ للحصه"
//        val dateOfAnnouncement = "العنوان : منشأة سلطان أمام محطة القطار"
//        val dateOfAnnouncement3 = "العنوان : منشأة سلطان أمام محطة القطار أعلى صالون Top 10"

//        val obj = AnnouncementPost(titleOFAnnouncement,imageOFAnnouncement,contentOfAnnouncement,dateOfAnnouncement,false)
//        val obj2 = AnnouncementPost(titleOFAnnouncement,imageOFAnnouncement,contentOfAnnouncement2,dateOfAnnouncement,true)
//        val obj3 = AnnouncementPost(titleOFAnnouncement2,imageOFAnnouncement3,contentOfAnnouncement3,dateOfAnnouncement3,true)
        val comparator = compareByDescending<AnnouncementPost> { it.statusNew }
        val listOFAds = listOf<AnnouncementPost>()
        val sortedList = listOFAds.sortedWith(comparator)
        announcementAdapter = AnnouncementPostAdapter(sortedList,requireContext())
        binding.recyclerViewAnnouncementPage.adapter = announcementAdapter
    }
}