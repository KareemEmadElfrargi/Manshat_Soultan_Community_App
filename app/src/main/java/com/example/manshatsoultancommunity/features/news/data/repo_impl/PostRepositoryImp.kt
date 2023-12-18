package com.example.manshatsoultancommunity.features.news.data.repo_impl

import android.content.Context
import com.example.manshatsoultancommunity.features.news.data.data_source.remote.IPostDataSourceRemote
import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.features.news.domain.repo.IPostRepository
import com.example.manshatsoultancommunity.util.Resource
import javax.inject.Inject

class PostRepositoryImp @Inject constructor(
    private val postDataSourceRemote : IPostDataSourceRemote,
    // --
    private val context : Context
):IPostRepository {
    override suspend fun getPost(): Resource<List<Post>> {
        return postDataSourceRemote.getPost()
    }
}