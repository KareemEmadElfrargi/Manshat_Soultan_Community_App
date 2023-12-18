package com.example.manshatsoultancommunity.features.news.domain.usecase

import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.util.Resource

interface IGetRipUseCase {
    suspend fun getRipPost() : Resource<List<Post>>
}