package com.example.manshatsoultancommunity.features.advertisement.data.data_source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nullable

@Entity(tableName = "AdsPostTable")
data class AdsPostEntity (
    @PrimaryKey
    val id:String = "",
    val titleAnnouncement:String?=null,
    val imageAnnouncement:String?=null,
    val imageData: ByteArray? = null,
    val descriptionAnnouncement:String?=null,
    val placePostedAnnouncement:String?=null,
    val statusNew:Boolean? = true,
    val datePostedAnnouncement:String?=null,
    /*additional*/
    val owner:String? =null,
    val dates:String?=null,
    val contactUs:String?=null

)