package com.example.manshatsoultancommunity.features.news.domain.repo

import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.util.Resource

interface IPostRepository {
    suspend fun getPost() : Resource<List<Post>>
}