package com.example.manshatsoultancommunity.features.news.data.model

data class PostCaption (
     val nameOfPublisher :String,
     val imageOfPublisher :Int?=null,
     val timeOfPost :String,
     val content :String,
     val status : Boolean = false
)