package com.example.manshatsoultancommunity.features.news.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.features.news.presentation.common.adapter.NormalPostAdapter
import com.example.manshatsoultancommunity.R
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

        val nameOfPublisher = "وفيات منشأة سلطان"
        val proFileImage = R.drawable.rip_image

        val listDiePeople = listOf<PostCaption> (
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "الاحد الساعة 5", content = "توفية الي رحمة الله تعالي الحاجة فتحية محمد حلاوة زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة رتيبة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "10:00", content = "توفية الي رحمة الله تعالي الحاجة سارة علي زوجة المرحوم محمد خالد والدفنة عند حضورة الجثة من مسجد الرحمة ولا عزاء للسيدات ", imageOfPublisher = proFileImage),
        )
        ripAdapter = NormalPostAdapter(listDiePeople.reversed(),requireContext())
        binding.recyclerViewRIPPage.adapter = ripAdapter
    }
}