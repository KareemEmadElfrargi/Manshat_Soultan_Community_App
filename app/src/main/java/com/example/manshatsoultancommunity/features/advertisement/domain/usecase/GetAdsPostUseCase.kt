package com.example.manshatsoultancommunity.features.advertisement.domain.usecase

import com.example.manshatsoultancommunity.features.advertisement.data.model.AnnouncementPost
import com.example.manshatsoultancommunity.features.advertisement.domain.repo.IAdsPostRepository
import com.example.manshatsoultancommunity.utils.Resource
import javax.inject.Inject

class GetAdsPostUseCase @Inject constructor(
    private val repository: IAdsPostRepository
) : IGetAdsPostUseCase {
    override suspend fun getAdsPostUseCase(): Resource<List<AnnouncementPost>> {
        val resource = repository.getAdsPost()

         return if (resource is Resource.Success) {
            val comparator = compareByDescending<AnnouncementPost> { it.statusNew }
             Resource.Success(resource.data?.sortedWith(comparator)!!)
        } else {
             return resource
        }
    }
}