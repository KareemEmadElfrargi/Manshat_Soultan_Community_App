package com.example.manshatsoultancommunity.features.advertisement.presentation.common.adapter

import com.example.manshatsoultancommunity.features.advertisement.data.model.AnnouncementPost

interface InteractionWithAds {
    fun onClickOnCardOfAds(adsPost:AnnouncementPost)
}