package com.example.manshatsoultancommunity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.databinding.FragmentAboutBinding
import com.example.manshatsoultancommunity.util.contactByWhatsApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutFragment:Fragment() {

    private lateinit var binding : FragmentAboutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            whatsAppDevOne.setOnClickListener {
                val phoneNumber = "1113461483"
                val countryCode = "+20"
                contactByWhatsApp(phoneNumber,countryCode)
            }
            whatsAppDevTwo.setOnClickListener {
                val phoneNumber = "1282579495"
                val countryCode = "+20"
                contactByWhatsApp(phoneNumber,countryCode)
            }
        }
    }





}