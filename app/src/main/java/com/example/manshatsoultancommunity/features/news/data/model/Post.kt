package com.example.manshatsoultancommunity.features.news.data.model

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@Parcelize
@IgnoreExtraProperties
data class Post (
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
     ):Parcelable
