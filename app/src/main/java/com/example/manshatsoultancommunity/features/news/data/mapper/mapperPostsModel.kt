package com.example.manshatsoultancommunity.features.news.data.mapper

import com.example.manshatsoultancommunity.features.news.data.data_source.local.entity.PostsEntity
import com.example.manshatsoultancommunity.features.news.data.model.Post

fun PostsEntity.toDomainModel() = Post(
    postId = postId,
    categoryType = categoryType,
    nameOfCategory = nameOfCategory,
    imageOfChannel = imageOfChannel,
    imageOfPost = imageOfPost,
    timeOfPost = timeOfPost,
    author = author,
    content = content,
    isPin = isPin,
    authorRating = authorRating
)