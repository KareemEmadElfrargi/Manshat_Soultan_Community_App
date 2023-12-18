package com.example.manshatsoultancommunity.features.news.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Post (
     val categoryType:String?=null,

     val nameOfCategory:String?=null,
     val imageOfChannel :Int?=null,
     val imageOfPost :String?=null,/*optional*/
     val timeOfPost :String?=null,
     val author :String?=null,
     val content :String?=null,
     val isPin : Boolean = false,
     )