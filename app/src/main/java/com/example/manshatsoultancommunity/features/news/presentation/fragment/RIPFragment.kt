package com.example.manshatsoultancommunity.features.news.presentation.fragment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.FragmentRipBinding
import com.example.manshatsoultancommunity.features.manager.presentation.fragment.ManageFragment
import com.example.manshatsoultancommunity.features.news.presentation.common.adapter.PostAdapter
import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.features.news.presentation.common.ViewModels.PostViewModel
import com.example.manshatsoultancommunity.features.news.presentation.common.adapter.InteractionWithOptionPost
import com.example.manshatsoultancommunity.util.Constants
import com.example.manshatsoultancommunity.util.Constants.CHILD_OF_POST_REALTIME
import com.example.manshatsoultancommunity.util.Resource
import com.example.manshatsoultancommunity.util.changeColorOfVectorIcon
import com.example.manshatsoultancommunity.util.showPopupMenu
import com.example.manshatsoultancommunity.util.showToast
import com.example.manshatsoultancommunity.util.showToastStyleWithPopUpMenu
import com.example.manshatsoultancommunity.util.visibilityGone
import com.example.manshatsoultancommunity.util.visibilityVisible
import com.github.chrisbanes.photoview.PhotoView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RIPFragment: Fragment(), InteractionWithOptionPost {
    private lateinit var binding : FragmentRipBinding
    private lateinit var ripAdapter : PostAdapter
    private lateinit var firebaseDatabase: FirebaseDatabase
    private var valueEventListenerRipPost: ValueEventListener? = null
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
        firebaseDatabase = FirebaseDatabase.getInstance()
        lifecycleScope.launch {
            viewModel.ripPostList.collect{ result ->
                when(result){
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        val listRipPost = result.data
                        Log.i("RIPFragment",result.data.toString())
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
        updatedData()
    }

    private fun updatedData() {
        valueEventListenerRipPost = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listOfRipPost = mutableListOf<Post>()
                for (ripPostSnapshot in snapshot.children) {
                    val ripPost = ripPostSnapshot.getValue(Post::class.java)
                    ripPost?.let { listOfRipPost.add(ripPost) }
                }
                setupRecycleView( listOfRipPost.filter { post ->
                    post.categoryType == Constants.CATEGORY_TYPE_RIP_POST
                })

            }
            override fun onCancelled(error: DatabaseError) {
                showToast(error.message)
            }
        }

        firebaseDatabase.reference.child(Constants.CHILD_OF_POST_REALTIME)
            .addValueEventListener(valueEventListenerRipPost!!)
    }
    private fun hideLoading() {
        binding.progressBarRipFragment.visibilityGone()
    }
    private fun setupRecycleView(listOfRipPost: List<Post>?) {
        listOfRipPost?.let {
            ripAdapter = PostAdapter(listOfRipPost, requireContext(),this@RIPFragment)
            binding.recyclerViewRIPPage.adapter = ripAdapter
        }
    }
    private fun showLoading() {
        binding.progressBarRipFragment.visibilityVisible()
    }


    override fun onLongPress(post: Post,view:View) {
        showEnlargedImageDialog(post)
    }

    private fun showEnlargedImageDialog(post:Post?) {
        post?.let { post ->
            val dialog = Dialog(requireContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen)
            dialog.setContentView(R.layout.dialog_enlarged_image)

            val photoView: PhotoView = dialog.findViewById(R.id.photoView)
            val captionOfPost :TextView = dialog.findViewById(R.id.captionOfPost)
            val closeButton: ImageView = dialog.findViewById(R.id.btnClose)

            Glide.with(this).load(post.imageOfPost).into(photoView)
            captionOfPost.text = post.content

            closeButton.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }

    }
    override fun onClickOptionPopupMenu(post: Post,view:View) {
        showPopupMenu(post,view,::deletePost,::updatePost){
            view as ImageView
            view.changeColorOfVectorIcon(requireContext(),R.drawable.circle_menu_3,R.color.black)
        }
    }
    private fun deletePost(post:Post,menuItem: MenuItem){
        firebaseDatabase.getReference(CHILD_OF_POST_REALTIME)
            .child(post.postId).removeValue()
            .addOnSuccessListener {
                showToastStyleWithPopUpMenu("تم حذف المنشور بنجاح",menuItem)
            }.addOnFailureListener {
                Log.e("Firebase", "Error deleting object: ${it.message.toString()}")
            }
    }
    private fun updatePost(post:Post,menuItem:MenuItem){
//        val bundle = Bundle().apply {
//            putParcelable("post",post)
//        }
//        findNavController().navigate(R.id.action_homeFragment_to_addPostFragment)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        firebaseDatabase.reference.child(Constants.CHILD_OF_POST_REALTIME).removeEventListener(valueEventListenerRipPost!!)
    }
}