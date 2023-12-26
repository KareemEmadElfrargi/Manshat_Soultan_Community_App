package com.example.manshatsoultancommunity.features.news.data.repo_impl

import android.content.Context
import android.util.Log
import com.example.manshatsoultancommunity.features.news.data.data_source.local.PostsLocalDataSource
import com.example.manshatsoultancommunity.features.news.data.data_source.remote.IPostDataSourceRemote
import com.example.manshatsoultancommunity.features.news.data.mapper.toDomainModel
import com.example.manshatsoultancommunity.features.news.data.mapper.toRoomEntity
import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.features.news.domain.repo.IPostRepository
import com.example.manshatsoultancommunity.util.Resource
import com.example.manshatsoultancommunity.util.isInternetAvailable
import com.example.manshatsoultancommunity.util.loadImageData
import com.example.manshatsoultancommunity.util.loadImageDataAsync
import javax.inject.Inject

class PostRepositoryImp @Inject constructor(
    private val postDataSourceRemote : IPostDataSourceRemote,
    private val postsLocalDataSource: PostsLocalDataSource,
    private val context : Context
):IPostRepository {
    override suspend fun getPost(): Resource<List<Post>> {
        return if (isInternetAvailable(context)) {
            try {
                postsLocalDataSource.deleteAllPosts()
                 val remotePostsList = postDataSourceRemote.getPost()
                var imageData:ByteArray? = null
                val dataListEntity = remotePostsList.data?.map {post ->
                    post.imageOfPost?.let {
                        imageData = loadImageDataAsync(it, context)
                    }
                    post.toRoomEntity(imageData)
                } ?: emptyList()
                postsLocalDataSource.insertPosts(dataListEntity)
                remotePostsList
            }catch (e: Exception){
                Resource.Error(e.message.toString())
            }
        } else {
            val localPosts = postsLocalDataSource.getAllPosts()
            return if (localPosts.isNotEmpty()) {
                val dataListDomain = localPosts.map {
                    it.toDomainModel()
                }
                Resource.Success(dataListDomain)
            } else {
                Resource.Error("لا يوجد اي منشورات لديك في الذاكرة")
            }
        }
    }
}