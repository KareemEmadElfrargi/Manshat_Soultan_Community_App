package com.example.manshatsoultancommunity.features.news.data.data_source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PostsTable")
data class PostsEntity(
    @PrimaryKey
    val postId:String = "",
    val categoryType:String?=null,
    val nameOfCategory:String?=null,
    val imageOfChannel :String?=null,
    val imageOfPost :String?=null,/*optional*/
    val timeOfPost :String?=null,
    val author :String?=null,
    val content :String?=null,
    val isPin : Boolean = false,
    val authorRating:Int?=null,
    val imageDataOfPost:ByteArray?=null
)
