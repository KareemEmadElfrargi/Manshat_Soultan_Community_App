package com.example.manshatsoultancommunity.features.Shopping.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.features.news.presentation.common.adapter.HomeViewPagerAdapter
import com.example.manshatsoultancommunity.databinding.FragmentHomeBinding
import com.example.manshatsoultancommunity.databinding.FragmentShoppingBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ShoppingFragment: Fragment() {
    private lateinit var binding: FragmentShoppingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        binding.emptyListAnimation.cancelAnimation()
        super.onDestroyView()
    }
}