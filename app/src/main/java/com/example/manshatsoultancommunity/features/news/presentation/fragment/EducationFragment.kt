package com.example.manshatsoultancommunity.features.news.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.NormalPostAdapter
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.FragmentEductionBinding
import com.example.manshatsoultancommunity.features.news.data.model.PostCaption

class EducationFragment: Fragment() {
    private lateinit var  binding : FragmentEductionBinding
    private lateinit var educationAdapter : NormalPostAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEductionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameOfSchool = "الشهيد شعبان المليجي"
        val proFileImage = R.drawable.img_shcool_profile
        val content2 = "نتيجة الشهادة الاعداداية قريبا علي التطبيق"
        val firstTwo = PostCaption(nameOfSchool,proFileImage,"10:00",content2,status = true)
        val listOFPostEduction = listOf(firstTwo)
        educationAdapter = NormalPostAdapter(listOFPostEduction,requireContext())
        binding.recyclerViewEductionPage.adapter  = educationAdapter


    }
}