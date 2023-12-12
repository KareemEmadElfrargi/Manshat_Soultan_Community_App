package com.example.manshatsoultancommunity.features.advertisement.data.model

data class AnnouncementPost(
    val titleAnnouncement:String,
    val imageAnnouncement:String?=null, // modify
    val descriptionAnnouncement:String,
    val placePostedAnnouncement:String,
    val statusNew:Boolean = true,
    val datePostedAnnouncement:String,
    /*additional*/
    val owner:String? =null,
    val dates:String?=null,
    val contactUs:String?=null
    /*More..*/
)
