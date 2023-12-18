package com.example.manshatsoultancommunity.features.news.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.manshatsoultancommunity.features.news.presentation.common.adapter.PostAdapter
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.FragmentRipBinding
import com.example.manshatsoultancommunity.features.advertisement.data.model.Advertisements
import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.features.news.presentation.common.ViewModels.PostViewModel
import com.example.manshatsoultancommunity.util.Resource
import com.example.manshatsoultancommunity.util.showToast
import com.example.manshatsoultancommunity.util.visibilityGone
import com.example.manshatsoultancommunity.util.visibilityVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint

class RIPFragment: Fragment() {
    private lateinit var binding : FragmentRipBinding
    private lateinit var ripAdapter : PostAdapter
    private val viewModel : PostViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRipBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nameOfPublisher = "وفيات منشأة سلطان"
        lifecycleScope.launch {
            viewModel.ripPostList.collect{ result ->
                when(result){
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        val listRipPost = result.data
                        setupRecycleView(listRipPost)
                    }
                    is Resource.Error -> {
                        hideLoading()
                        val errorMessage = result.message.toString()
                        showToast(errorMessage)
                    }
                    is Resource.Unspecified -> Unit
                }
            }
        }

    }

    private fun hideLoading() {
        binding.progressBarRipFragment.visibilityGone()
    }

    private fun setupRecycleView(listOfRipPost: List<Post>?) {
        listOfRipPost?.let {
            ripAdapter = PostAdapter(listOfRipPost, requireContext())
            binding.recyclerViewRIPPage.adapter = ripAdapter
        }
    }

    private fun showLoading() {
        binding.progressBarRipFragment.visibilityVisible()
    }
}