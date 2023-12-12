package com.example.manshatsoultancommunity.features.advertisement.data.mapper

import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.AdsPostEntity
import com.example.manshatsoultancommunity.features.advertisement.data.model.AnnouncementPost

fun AdsPostEntity.toDomainModel(): AnnouncementPost {
    return AnnouncementPost(
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
