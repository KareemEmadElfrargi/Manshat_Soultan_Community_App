package com.example.manshatsoultancommunity.features.news.data.model

data class AnnouncementPost(
    val titleAnnouncement:String,
    val imageAnnouncement:Int,
    val descriptionAnnouncement:String,
    val datePostedAnnouncement:String,
    val statusNew:Boolean = true
)
