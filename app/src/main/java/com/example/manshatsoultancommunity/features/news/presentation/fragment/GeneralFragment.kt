package com.example.manshatsoultancommunity.features.news.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.manshatsoultancommunity.databinding.FragmentGeneralBinding
import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.features.news.presentation.common.ViewModels.PostViewModel
import com.example.manshatsoultancommunity.features.news.presentation.common.adapter.PostAdapter
import com.example.manshatsoultancommunity.util.Constants
import com.example.manshatsoultancommunity.util.Resource
import com.example.manshatsoultancommunity.util.showToast
import com.example.manshatsoultancommunity.util.visibilityGone
import com.example.manshatsoultancommunity.util.visibilityVisible
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class GeneralFragment: Fragment() {
    private lateinit var binding : FragmentGeneralBinding
    private lateinit var homeAdapter : PostAdapter
    private lateinit var firebaseDatabase: FirebaseDatabase
    private var valueEventListenerPosts: ValueEventListener? = null
    private val viewModel : PostViewModel by viewModels()
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
        firebaseDatabase = FirebaseDatabase.getInstance()
        lifecycleScope.launch {
            viewModel.generalPostList.collect{ result ->
                when(result){
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        val listPosts = result.data
                        if (listPosts?.size==0){
                            binding.emptyListAnimation.visibilityVisible()
                        }else {
                            binding.emptyListAnimation.visibilityGone()
                        }
                        Log.i("SportFragment",result.data.toString())
                        setupRecycleView(listPosts)
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
        updatedData()
    }
    private fun updatedData() {
        valueEventListenerPosts = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listOfPosts = mutableListOf<Post>()
                for (postsSnapshot in snapshot.children) {
                    val posts = postsSnapshot.getValue(Post::class.java)

                    posts?.let {
                        listOfPosts.add(posts)
                    }
                }
                if (listOfPosts.isEmpty()){
                    binding.emptyListAnimation.visibilityVisible()
                }else{
                    binding.emptyListAnimation.visibilityGone()
                }

                setupRecycleView(listOfPosts)
            }

            override fun onCancelled(error: DatabaseError) {
                showToast(error.message)
            }

        }

        firebaseDatabase.reference.child(Constants.CHILD_OF_POST_REALTIME)
            .addValueEventListener(valueEventListenerPosts!!)
    }
    private fun setupRecycleView(listPosts: List<Post>?) {
        listPosts?.let {
            homeAdapter = PostAdapter(listPosts, requireContext(),null)
            binding.recyclerViewGeneralPage.adapter = homeAdapter
        }
    }
    private fun hideLoading() {
        binding.progressBarGeneralFragment.visibilityGone()
    }
    private fun showLoading() {
        binding.progressBarGeneralFragment.visibilityVisible()
    }

    override fun onDestroyView() {
        binding.emptyListAnimation.cancelAnimation()
        firebaseDatabase.reference.child(Constants.CHILD_OF_POST_REALTIME).removeEventListener(valueEventListenerPosts!!)
        super.onDestroyView()
    }
}