package com.example.manshatsoultancommunity.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.IAdvertisementsDao
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.Entity.AdvertisementsEntity
import com.example.manshatsoultancommunity.features.news.data.data_source.local.IPostsDao
import com.example.manshatsoultancommunity.features.news.data.data_source.local.entity.PostsEntity

@Database(entities = [AdvertisementsEntity::class,PostsEntity::class], version = 1)
abstract class AppDatabase :RoomDatabase() {
    abstract fun advertisementsDao():IAdvertisementsDao
    abstract fun postsDao():IPostsDao
}
