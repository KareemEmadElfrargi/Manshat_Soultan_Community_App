package com.example.manshatsoultancommunity.features.Intro.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.FragmentIntroBinding
import com.example.manshatsoultancommunity.databinding.FragmentLoginAdminBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class IntroductionFragment: Fragment() {
    private lateinit var binding : FragmentIntroBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStartIntro.setOnClickListener {
            findNavController().navigate(R.id.action_introductionFragment_to_optionLoginFragment)
        }

    }
}