package com.example.manshatsoultancommunity.features.Intro.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.FragmentIntroBinding
import com.example.manshatsoultancommunity.databinding.FragmentLoginAdminBinding
import com.example.manshatsoultancommunity.features.news.presentation.common.NewsActivity
import com.example.manshatsoultancommunity.utils.Constants.Auth_STATUS
import com.example.manshatsoultancommunity.utils.SharedPreferencesManager
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

        val authStatus = SharedPreferencesManager(requireContext()).getString(Auth_STATUS)
        if (!authStatus.isNullOrBlank()){
            startActivity(Intent(requireContext(),NewsActivity::class.java))
            activity?.finish()
        }
        binding.buttonStartIntro.setOnClickListener {
            findNavController().navigate(R.id.action_introductionFragment_to_optionLoginFragment)
        }

    }
}