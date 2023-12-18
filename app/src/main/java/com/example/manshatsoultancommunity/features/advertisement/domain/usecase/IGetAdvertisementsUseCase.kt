package com.example.manshatsoultancommunity.features.advertisement.domain.usecase

import com.example.manshatsoultancommunity.features.advertisement.data.model.Advertisements
import com.example.manshatsoultancommunity.util.Resource

interface IGetAdvertisementsUseCase {
    suspend fun getAdvertisementsUseCase(): Resource<List<Advertisements>>
}