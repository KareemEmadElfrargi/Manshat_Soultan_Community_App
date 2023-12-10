package com.example.manshatsoultancommunity.features.news.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.databinding.FragmentRipBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class RIPFragment: Fragment() {

    private lateinit var binding : FragmentRipBinding
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
    }
}