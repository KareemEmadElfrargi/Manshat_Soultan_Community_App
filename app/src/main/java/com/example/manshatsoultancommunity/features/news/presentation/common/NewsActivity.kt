package com.example.manshatsoultancommunity.features.news.presentation.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint

class NewsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    val inComingText = "⚡ الكهرباء هتقطع ان شاء الله النهاردا من الساعة 1 الي الساعة 3 عصراً"

    val formatTextIncoming = "\uD83D\uDEA8 عاجل  $inComingText"
    lifecycleScope.launch(Dispatchers.Default) {
        binding.animationTitle.apply {
            marqueeRepeatLimit = -1 // For infinite scrolling
            isSingleLine = true
            isSelected = true
            text = formatTextIncoming
        }
    }
    }
    override fun onResume() {
        super.onResume()
        val navController = findNavController(R.id.fragmentContainerNews)
        binding.navigationHomeNewsActivity.setupWithNavController(navController)
    }

}