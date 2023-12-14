package com.example.manshatsoultancommunity.features.advertisement.data.mapper

import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.AdsPostEntity
import com.example.manshatsoultancommunity.features.advertisement.data.model.AnnouncementPost


fun AnnouncementPost.toRoomEntity(imageDataX:ByteArray): AdsPostEntity {
    return AdsPostEntity(
        id = idAds,
        titleAnnouncement = titleAnnouncement,
        imageAnnouncement = imageAnnouncement,
        imageData =imageDataX,
        descriptionAnnouncement = descriptionAnnouncement,
        placePostedAnnouncement = placePostedAnnouncement,
        statusNew = statusNew,
        datePostedAnnouncement = datePostedAnnouncement,
        owner = owner,
        dates = dates,
        contactUs = contactUs
    )
}
