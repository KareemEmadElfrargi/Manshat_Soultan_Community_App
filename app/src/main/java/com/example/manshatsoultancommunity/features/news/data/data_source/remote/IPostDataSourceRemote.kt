package com.example.manshatsoultancommunity.features.news.data.data_source.remote

import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.util.Resource

interface IPostDataSourceRemote {
    suspend fun getPost() : Resource<List<Post>>
}