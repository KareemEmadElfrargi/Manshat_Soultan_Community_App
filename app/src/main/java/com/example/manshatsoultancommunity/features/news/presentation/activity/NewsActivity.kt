package com.example.manshatsoultancommunity.features.news.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.ActivityNewsBinding
import com.example.manshatsoultancommunity.util.Constants.CODE_AUTH_KEY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.apache.commons.text.StringEscapeUtils

@AndroidEntryPoint

class NewsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inComingText = "⚡ الكهرباء هتقطع ان شاء الله النهاردا من الساعة 1 الي الساعة 3 عصراً"


        val formatTextIncoming =
            "${getString(R.string.emoji_important)} ${getString(R.string.important)} $inComingText"
        lifecycleScope.launch(Dispatchers.Default) {
            binding.animationTitle.apply {
                isSingleLine = true
                isSelected = true
                val unescapedText = StringEscapeUtils.unescapeJava(formatTextIncoming)
                text = unescapedText
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val navController = findNavController(R.id.fragmentContainerNews)
        binding.navigationHomeNewsActivity.setupWithNavController(navController)
    }

}