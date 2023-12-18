package com.example.manshatsoultancommunity.features.advertisement.domain.repo

import com.example.manshatsoultancommunity.features.advertisement.data.model.Advertisements
import com.example.manshatsoultancommunity.util.Resource

interface IAdvertisementsRepository {
    suspend fun getAdsPost(): Resource<List<Advertisements>>
}