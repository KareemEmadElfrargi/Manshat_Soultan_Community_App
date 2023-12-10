package com.example.manshatsoultancommunity.features.Intro.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.databinding.FragmentLoginAdminBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class AdminLoginFragment: Fragment() {
    private lateinit var binding :  FragmentLoginAdminBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginAdminBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}