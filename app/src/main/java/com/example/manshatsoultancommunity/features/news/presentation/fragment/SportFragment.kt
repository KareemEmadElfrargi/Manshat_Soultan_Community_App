package com.example.manshatsoultancommunity.features.news.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.NormalPostAdapter
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.SportPostAdapter
import com.example.manshatsoultancommunity.databinding.FragmentAdsBinding
import com.example.manshatsoultancommunity.databinding.FragmentSportBinding
import com.example.manshatsoultancommunity.features.news.data.model.AppointmentMatch
import com.example.manshatsoultancommunity.features.news.data.model.PostCaption
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class SportFragment: Fragment() {
    private lateinit var binding : FragmentSportBinding
    private lateinit var sportAdapter : SportPostAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSportBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nameOfPublisher = "مركز شباب منشأة سلطان"
        val proFileImage = R.drawable.sport_image_profile
        val listSportPosts = listOf (
            PostCaption(nameOfPublisher = nameOfPublisher , timeOfPost = "10:00", content = "بكرا ان شاء الله افتتاح دورة المرحوم له باذن الله سامح زيتون في مركز شباب منشاة سلطان", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "11:00", content = "اول فريق هيلعب منشاة سلطان أ ومنشاة سلطان ب الساعة 4 العصر " ,imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "1:00", content = "انتخابات مجلس ادارة النادي ان شاء الله يوم الحد الساعة 2 لو حد حابب يشارك ⚽ ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "1:00", content = "انهاردا مفيش حجز عشان الجو يشباب , امضاء عمك فوزي", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "11:00", content = "اول فريق هيلعب منشاة سلطان أ ومنشاة سلطان ب الساعة 4 العصر ", imageOfPublisher = proFileImage),
            PostCaption(nameOfPublisher = nameOfPublisher, timeOfPost = "1:00", content = "انهاردا مفيش حجز عشان الجو يشباب , امضاء عمك فوزي", imageOfPublisher = proFileImage, status = true),
            )
        val listAppointmentMatch = listOf(
            AppointmentMatch("منشأة سلطان","تتا","20:20","الحد"),
        )
        val objAppointmentMatch = AppointmentMatch("منشأة سلطان","تتا","20:20","الحد")
        sportAdapter = SportPostAdapter(listSportPosts,listAppointmentMatch,requireContext())
        binding.recyclerViewSportPage.adapter = sportAdapter
    }
}