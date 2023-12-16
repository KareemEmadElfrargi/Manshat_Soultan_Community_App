package com.example.manshatsoultancommunity.features.advertisement.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Advertisements(
    val id:String = "",
    val titleOfAdvertisement:String?=null,
    val imageOfAdvertisement:String?=null,
    val descriptionOfAdvertisement:String?=null,
    val locationOfAdvertisement:String?=null,
    val isPinAdvertisement:Boolean? = false,
    val datePostedAdvertisement:String?=null,
    /*additional*/
    val ownerOfAdvertisement:String? =null,
    val datesOfAdvertisement:String?=null,
    val numberOfOwnerOfAdvertisement:String?=null
    /*More..*/
)
