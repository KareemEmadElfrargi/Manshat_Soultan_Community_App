package com.example.manshatsoultancommunity.features.advertisement.data.repo_impl

import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.AdsPostLocalDataSource
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.remote.IAdsPostDataSource
import com.example.manshatsoultancommunity.features.advertisement.data.mapper.toDomainModel
import com.example.manshatsoultancommunity.features.advertisement.data.mapper.toRoomEntity
import com.example.manshatsoultancommunity.features.advertisement.data.model.AnnouncementPost
import com.example.manshatsoultancommunity.features.advertisement.domain.repo.IAdsPostRepository
import com.example.manshatsoultancommunity.utils.Resource
import com.google.api.ResourceProto.resource
import javax.inject.Inject

class AdsPostRepository @Inject constructor(
    private val adsPostDataSource : IAdsPostDataSource,
    private val dataSource: AdsPostLocalDataSource
) : IAdsPostRepository {
    override suspend fun getAdsPost(): Resource<List<AnnouncementPost>> {

        val remoteAdsPost = adsPostDataSource.getAdsPost()
        return if (remoteAdsPost is Resource.Success) {
            dataSource.insertAdsPost(
                remoteAdsPost.data?.map {
                    it.toRoomEntity()
                }!!
            )
            remoteAdsPost

        } else {
            val localAdsPost = dataSource.getAllAdsPosts()
            return if (localAdsPost.isNotEmpty()) {
                Resource.Success(localAdsPost.map {
                    it.toDomainModel()
                })
            } else {
                Resource.Error(remoteAdsPost.message.toString())
            }
        }

    }
}