package com.example.manshatsoultancommunity.features.news.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.manshatsoultancommunity.features.news.presentation.common.adapter.PostAdapter
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.FragmentEductionBinding
import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.features.news.presentation.common.ViewModels.PostViewModel
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
class EducationFragment: Fragment() {
    private lateinit var  binding : FragmentEductionBinding
    private lateinit var educationAdapter : PostAdapter
    private lateinit var firebaseDatabase: FirebaseDatabase
    private var valueEventListenerEducationPost: ValueEventListener? = null
    private val viewModel : PostViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEductionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseDatabase = FirebaseDatabase.getInstance()

        lifecycleScope.launch {
            viewModel.educationPostList.collect{ result ->
                when(result){
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        val listEducationPost = result.data
                        if (listEducationPost!!.isEmpty()){
                            binding.emptyListAnimation.visibilityVisible()
                            hideOtherViews()
                        }else {
                            binding.emptyListAnimation.visibilityGone()
                            showOtherViews()
                            setupRecycleView(listEducationPost)
                        }

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
        valueEventListenerEducationPost = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listOfEducationPost = mutableListOf<Post>()
                for (educationPostSnapshot in snapshot.children) {
                    val educationPost = educationPostSnapshot.getValue(Post::class.java)

                    educationPost?.let {
                        listOfEducationPost.add(educationPost)
                    }
                }
                val finalList = listOfEducationPost.filter { post ->
                    post.categoryType == Constants.CATEGORY_TYPE_EDUCATION_POST
                }

                if (finalList.isEmpty()){
                    binding.emptyListAnimation.visibilityVisible()
                }else{
                    binding.emptyListAnimation.visibilityGone()
                }

                setupRecycleView(finalList)
            }

            override fun onCancelled(error: DatabaseError) {
                showToast(error.message)
            }

        }

        firebaseDatabase.reference.child(Constants.CHILD_OF_POST_REALTIME)
            .addValueEventListener(valueEventListenerEducationPost!!)
    }
    private fun setupRecycleView(listOfEducationPost: List<Post>?) {
        listOfEducationPost?.let {
            educationAdapter = PostAdapter(listOfEducationPost.reversed(),requireContext(),null)
            binding.recyclerViewEductionPage.adapter = educationAdapter
        }
    }
    private fun showLoading() {
        binding.progressBarEducationFragment.visibilityVisible()
    }

    private fun hideLoading() {
        binding.progressBarEducationFragment.visibilityGone()
    }
    private fun hideOtherViews() {
        binding.recyclerViewEductionPage.visibilityGone()
    }
    private fun showOtherViews() {
        binding.recyclerViewEductionPage.visibilityVisible()
    }
    override fun onDestroyView() {
        binding.emptyListAnimation.cancelAnimation()
        firebaseDatabase.reference.child(Constants.CHILD_OF_POST_REALTIME).removeEventListener(valueEventListenerEducationPost!!)
        super.onDestroyView()
    }
}