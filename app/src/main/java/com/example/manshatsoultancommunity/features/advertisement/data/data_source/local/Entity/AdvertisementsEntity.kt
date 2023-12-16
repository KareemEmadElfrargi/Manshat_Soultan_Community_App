package com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AnnouncementsTable")
data class AdvertisementsEntity (
    @PrimaryKey
    var id:String = "",
    var titleOfAdvertisement:String?=null,
    var imageOfAdvertisement:String?=null,
    var imageDataOfAdvertisement: ByteArray? = null,
    var descriptionOfAdvertisement:String?=null,
    var locationOfAdvertisement:String?=null,
    var isPinAdvertisement:Boolean? = false,
    var datePostedAdvertisement:String?=null,
    /*additional*/
    var ownerOfAdvertisement:String? =null,
    var datesOfAdvertisement:String?=null,
    var numberOfOwnerOfAdvertisement:String?=null
    /*More..*/

)