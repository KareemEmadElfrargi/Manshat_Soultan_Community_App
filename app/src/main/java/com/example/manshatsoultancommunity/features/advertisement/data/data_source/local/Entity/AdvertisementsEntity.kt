package com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AnnouncementsTable")
data class AdvertisementsEntity (
    @PrimaryKey
    val id:String = "",
    val titleOfAdvertisement:String?=null,
    val imageOfAdvertisement:String?=null,
    val imageDataOfAdvertisement: ByteArray? = null,
    val descriptionOfAdvertisement:String?=null,
    val locationOfAdvertisement:String?=null,
    val isPinAdvertisement:Boolean? = true,
    val datePostedAdvertisement:String?=null,
    /*additional*/
    val ownerOfAdvertisement:String? =null,
    val datesOfAdvertisement:String?=null,
    val numberOfOwnerOfAdvertisement:String?=null
    /*More..*/

)