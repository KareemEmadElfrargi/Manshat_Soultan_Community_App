package com.example.manshatsoultancommunity.features.news.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.databinding.FragmentAboutBinding
import com.example.manshatsoultancommunity.databinding.FragmentAddPostBinding
import com.example.manshatsoultancommunity.databinding.FragmentGeneralBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class GeneralFragment: Fragment() {
    private lateinit var binding : FragmentGeneralBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGeneralBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}