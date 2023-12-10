package com.example.manshatsoultancommunity.features.news.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.NormalPostAdapter
import com.example.manshatsoultancommunity.databinding.FragmentRipBinding
import com.example.manshatsoultancommunity.features.news.data.model.PostCaption
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class RIPFragment: Fragment() {
    private lateinit var binding : FragmentRipBinding
    private lateinit var ripAdapter : NormalPostAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRipBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameOfPublisher = "واجب عزاء منشاة سلطان"
        val listDiePeople = listOf<PostCaption> (
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة فتحية محمد حلاوة زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات "),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات "),
        )
        ripAdapter = NormalPostAdapter(listDiePeople,requireContext())
        binding.recyclerViewRIPPage.adapter = ripAdapter
    }
}