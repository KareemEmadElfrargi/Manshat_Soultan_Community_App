package com.example.manshatsoultancommunity.features.advertisement.domain.usecase

import com.example.manshatsoultancommunity.features.advertisement.data.model.Advertisements
import com.example.manshatsoultancommunity.features.advertisement.domain.repo.IAdvertisementsRepository
import com.example.manshatsoultancommunity.utils.Resource
import javax.inject.Inject

class GetAdvertisementsUseCase @Inject constructor(
    private val repository: IAdvertisementsRepository
) : IGetAdvertisementsUseCase {
    override suspend fun getAdvertisementsUseCase(): Resource<List<Advertisements>> {
        val resource = repository.getAdsPost()
         return if (resource is Resource.Success) {
             val sortedList = resource.data?.sortedByDescending { it.isPinAdvertisement }
             Resource.Success(sortedList.orEmpty())
        } else {
              resource
        }
    }
}