package com.example.manshatsoultancommunity.features.advertisement.data.repo_impl

import com.example.manshatsoultancommunity.features.advertisement.data.data_source.remote.IAdsPostDataSource
import com.example.manshatsoultancommunity.features.advertisement.data.model.AnnouncementPost
import com.example.manshatsoultancommunity.features.advertisement.domain.repo.IAdsPostRepository
import com.example.manshatsoultancommunity.utils.Resource
import javax.inject.Inject

class AdsPostRepository @Inject constructor(
    private val adsPostDataSource : IAdsPostDataSource
) : IAdsPostRepository {
    override suspend fun getAdsPost(): Resource<List<AnnouncementPost>> {
        return adsPostDataSource.getAdsPost() //logic here
    }
}