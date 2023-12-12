package com.example.manshatsoultancommunity.features.advertisement.domain.repo

import com.example.manshatsoultancommunity.features.advertisement.data.model.AnnouncementPost
import com.example.manshatsoultancommunity.utils.Resource

interface IAdsPostRepository {
    suspend fun getAdsPost(): Resource<List<AnnouncementPost>>
}