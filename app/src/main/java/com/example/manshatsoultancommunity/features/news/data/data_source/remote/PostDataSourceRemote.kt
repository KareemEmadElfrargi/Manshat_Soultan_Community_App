package com.example.manshatsoultancommunity.features.news.data.data_source.remote

import com.example.manshatsoultancommunity.features.advertisement.data.model.Advertisements
import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.util.Constants.CHILD_OF_POST_REALTIME
import com.example.manshatsoultancommunity.util.Resource
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PostDataSourceRemote @Inject constructor(
    private val remoteDatabase: FirebaseDatabase
) : IPostDataSourceRemote {
    override suspend fun getPost(): Resource<List<Post>> {
        val postsList = mutableListOf<Post>()
       return try {
           val databaseRef = remoteDatabase.reference.child(CHILD_OF_POST_REALTIME).get().await()
           for (objectOfPosts in databaseRef.children) {
               if (objectOfPosts.value is Map<*, *>) {
                   val post = objectOfPosts.getValue(Post::class.java)
                   post?.let {postsList.add(post)}
               }
           }
           Resource.Success(postsList)
       } catch (exception :Exception){
           Resource.Error(exception.message.toString())
       }
    }
}