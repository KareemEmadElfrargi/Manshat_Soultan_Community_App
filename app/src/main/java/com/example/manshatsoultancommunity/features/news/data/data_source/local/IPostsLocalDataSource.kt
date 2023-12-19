package com.example.manshatsoultancommunity.features.news.data.data_source.local

import com.example.manshatsoultancommunity.features.news.data.data_source.local.entity.PostsEntity


interface IPostsLocalDataSource {
    suspend fun insertPosts(posts:List<PostsEntity>)

    suspend fun deleteAllPosts()

    suspend fun getAllPosts(): List<PostsEntity>
}