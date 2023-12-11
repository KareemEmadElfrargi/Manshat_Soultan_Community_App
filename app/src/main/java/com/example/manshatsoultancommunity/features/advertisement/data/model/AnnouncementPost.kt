package com.example.manshatsoultancommunity.features.advertisement.data.model

data class AnnouncementPost(
    val titleAnnouncement:String,
    val imageAnnouncement:Int,
    val descriptionAnnouncement:String,
    val datePostedAnnouncement:String,
    val statusNew:Boolean = true
)
