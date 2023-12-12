package com.example.manshatsoultancommunity.features.advertisement.data.data_source.remote

import com.example.manshatsoultancommunity.features.advertisement.data.model.AnnouncementPost
import com.example.manshatsoultancommunity.utils.Resource

interface IAdsPostDataSource {
    suspend fun getAdsPost(): Resource<List<AnnouncementPost>>
}