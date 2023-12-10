package com.example.manshatsoultancommunity.features.Intro.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.databinding.FragmentLoginOptionBinding
import com.example.manshatsoultancommunity.features.news.presentation.common.NewsActivity
import com.example.manshatsoultancommunity.utils.dailogs.setupButtonSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class OptionLoginFragment : Fragment(){

    private lateinit var binding : FragmentLoginOptionBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginOptionBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonUserAccountOptions.setOnClickListener {
            Intent(requireActivity(),NewsActivity::class.java).also { intent ->
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            Toast.makeText(requireContext(),"Login as User",Toast.LENGTH_SHORT).show()
        }
        binding.buttonAdminAccountOptions.setOnClickListener {
                setupButtonSheetDialog { code ->
                    Toast.makeText(requireContext(),code,Toast.LENGTH_SHORT).show()
                }
        }
    }
}