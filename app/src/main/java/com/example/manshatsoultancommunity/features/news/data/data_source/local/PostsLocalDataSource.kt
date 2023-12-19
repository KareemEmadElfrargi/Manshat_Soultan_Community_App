package com.example.manshatsoultancommunity.features.news.data.data_source.local

import com.example.manshatsoultancommunity.features.news.data.data_source.local.entity.PostsEntity
import javax.inject.Inject

class PostsLocalDataSource @Inject constructor(
    private val postsDao: IPostsDao
)  : IPostsLocalDataSource {
    override suspend fun insertPosts(posts: List<PostsEntity>) {
        postsDao.insertPosts(posts)
    }

    override suspend fun deleteAllPosts() {
        postsDao.deleteAllPosts()
    }

    override suspend fun getAllPosts(): List<PostsEntity> {
        return postsDao.getAllPosts()
    }
}