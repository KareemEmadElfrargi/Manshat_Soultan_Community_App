package com.example.manshatsoultancommunity.features.news.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.manshatsoultancommunity.features.news.data.data_source.local.entity.PostsEntity

@Dao
interface IPostsDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts:List<PostsEntity>)

    @Query("DELETE FROM PostsTable")
    suspend fun deleteAllPosts()

    @Query("SELECT * FROM PostsTable")
    suspend fun getAllPosts(): List<PostsEntity>

}