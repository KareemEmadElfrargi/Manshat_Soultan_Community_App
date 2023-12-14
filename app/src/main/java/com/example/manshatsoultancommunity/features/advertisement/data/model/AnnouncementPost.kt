package com.example.manshatsoultancommunity.features.advertisement.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class AnnouncementPost(
    val idAds:String = "",
    val titleAnnouncement:String?=null,
    val imageAnnouncement:String?=null,
    val descriptionAnnouncement:String?=null,
    val placePostedAnnouncement:String?=null,
    val statusNew:Boolean? = true,
    val datePostedAnnouncement:String?=null,
    /*additional*/
    val owner:String? =null,
    val dates:String?=null,
    val contactUs:String?=null
    /*More..*/
)
