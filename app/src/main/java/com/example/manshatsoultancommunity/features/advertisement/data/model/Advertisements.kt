package com.example.manshatsoultancommunity.features.advertisement.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Advertisements(
    var id:String = "",
    var titleOfAdvertisement:String?=null,
    var imageOfAdvertisement:String?=null,
    var descriptionOfAdvertisement:String?=null,
    var locationOfAdvertisement:String?=null,
    var isPinAdvertisement:Boolean? = false,
    val datePostedAdvertisement:String?=null,
    /*additional*/
    var ownerOfAdvertisement:String? =null,
    var datesOfAdvertisement:String?=null,
    var numberOfOwnerOfAdvertisement:String?=null
    /*More..*/
)
