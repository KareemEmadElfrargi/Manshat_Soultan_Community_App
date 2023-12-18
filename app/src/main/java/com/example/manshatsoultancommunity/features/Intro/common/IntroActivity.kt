package com.example.manshatsoultancommunity.features.Intro.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.ActivityIntroBinding
import com.example.manshatsoultancommunity.util.TransitionListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroActivity : AppCompatActivity(),TransitionListener {
    private lateinit var binding : ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(requireNotNull(binding.root))
    }

    override fun applyTransition() {
        overridePendingTransition(R.anim.from_right, R.anim.to_left)
    }
}