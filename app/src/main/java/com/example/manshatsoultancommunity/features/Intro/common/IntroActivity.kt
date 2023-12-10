package com.example.manshatsoultancommunity.features.Intro.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.ActivityIntroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroActivity : AppCompatActivity() {
    private lateinit var binding : ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(requireNotNull(binding.root))
    }
}