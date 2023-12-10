package com.example.manshatsoultancommunity.features.news.presentation.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class NewsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.animationTitle.apply {
            setSingleLine()
            isSelected = true
            text ="مبارة اليوم ⚽ بين منشأة سلطان \uD83C\uDD9A سنجرج الساعة 4 عصراً"
        }

    }

    override fun onResume() {
        super.onResume()
        val navController = findNavController(R.id.fragmentContainerNews)
        binding.navigationHomeNewsActivity.setupWithNavController(navController)
    }

}