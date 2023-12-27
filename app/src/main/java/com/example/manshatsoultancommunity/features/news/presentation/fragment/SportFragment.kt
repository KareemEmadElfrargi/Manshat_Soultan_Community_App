package com.example.manshatsoultancommunity.features.news.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.features.news.presentation.common.adapter.SportPostAdapter
import com.example.manshatsoultancommunity.databinding.FragmentSportBinding
import com.example.manshatsoultancommunity.features.news.data.model.AppointmentMatch
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

class SportFragment: Fragment() {
    private lateinit var binding : FragmentSportBinding
    private lateinit var sportAdapter : PostAdapter
    private lateinit var firebaseDatabase: FirebaseDatabase
    private var valueEventListenerSportPost: ValueEventListener? = null
    private val viewModel : PostViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSportBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseDatabase = FirebaseDatabase.getInstance()

        lifecycleScope.launch {
            viewModel.sportPostList.collect{ result ->
                when(result){
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        val listSportPost = result.data
//                        if (listSportPost?.size==0){
//                            binding.emptyListAnimation.visibilityVisible()
//                        }else {
//                            binding.emptyListAnimation.visibilityGone()
//                        }
                        Log.i("SportFragment",result.data.toString())
                        setupRecycleView(listSportPost)
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
        valueEventListenerSportPost = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listOfSportPost = mutableListOf<Post>()
                for (sportPostSnapshot in snapshot.children) {
                    val sportPost = sportPostSnapshot.getValue(Post::class.java)


                    sportPost?.let {
                        listOfSportPost.add(sportPost)
                    }
                }
                val finalList = listOfSportPost.filter { post ->
                    post.categoryType == Constants.CATEGORY_TYPE_SPORT_POST
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
            .addValueEventListener(valueEventListenerSportPost!!)
    }
    private fun hideLoading() {
        binding.progressBarSportFragment.visibilityGone()
    }

    private fun setupRecycleView(listOfSportPost: List<Post>?) {
        listOfSportPost?.let {
            sportAdapter = PostAdapter(listOfSportPost.reversed(), requireContext(),null)
            binding.recyclerViewSportPage.adapter = sportAdapter
        }
    }
    private fun showLoading() {
        binding.progressBarSportFragment.visibilityVisible()
    }
    override fun onDestroy() {
        super.onDestroy()
    }
    override fun onDestroyView() {
        binding.emptyListAnimation.cancelAnimation()
        firebaseDatabase.reference.child(Constants.CHILD_OF_POST_REALTIME).removeEventListener(valueEventListenerSportPost!!)
        super.onDestroyView()
    }
}