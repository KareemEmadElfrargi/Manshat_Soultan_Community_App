package com.example.manshatsoultancommunity.features.advertisement.data.data_source.remote

import com.example.manshatsoultancommunity.features.advertisement.data.model.Advertisements
import com.example.manshatsoultancommunity.util.Resource

interface IAdvertisementsDataSourceRemote {
    suspend fun getAdvertisements(): Resource<List<Advertisements>>
}