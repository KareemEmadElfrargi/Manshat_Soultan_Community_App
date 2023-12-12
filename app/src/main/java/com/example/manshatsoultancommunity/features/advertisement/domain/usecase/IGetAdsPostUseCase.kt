package com.example.manshatsoultancommunity.features.advertisement.domain.usecase

import com.example.manshatsoultancommunity.features.advertisement.data.model.AnnouncementPost
import com.example.manshatsoultancommunity.utils.Resource

interface IGetAdsPostUseCase {
    suspend fun getAdsPostUseCase(): Resource<List<AnnouncementPost>>
}