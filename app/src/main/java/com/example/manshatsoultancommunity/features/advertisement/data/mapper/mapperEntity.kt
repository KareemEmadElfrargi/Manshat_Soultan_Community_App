package com.example.manshatsoultancommunity.features.advertisement.data.mapper

import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.AdsPostEntity
import com.example.manshatsoultancommunity.features.advertisement.data.model.AnnouncementPost


fun AnnouncementPost.toRoomEntity(): AdsPostEntity {
    return AdsPostEntity(
        titleAnnouncement = titleAnnouncement,
        imageAnnouncement = imageAnnouncement,
        descriptionAnnouncement = descriptionAnnouncement,
        placePostedAnnouncement = placePostedAnnouncement,
        statusNew = statusNew,
        datePostedAnnouncement = datePostedAnnouncement,
        owner = owner,
        dates = dates,
        contactUs = contactUs
    )
}