package com.example.manshatsoultancommunity.features.advertisement.data.repo_impl

import android.content.Context
import android.util.Log
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.IAdvertisementsLocalDataSource
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.remote.IAdvertisementsDataSourceRemote
import com.example.manshatsoultancommunity.features.advertisement.data.mapper.toDomainModel
import com.example.manshatsoultancommunity.features.advertisement.data.mapper.toRoomEntity
import com.example.manshatsoultancommunity.features.advertisement.data.model.Advertisements
import com.example.manshatsoultancommunity.features.advertisement.domain.repo.IAdvertisementsRepository
import com.example.manshatsoultancommunity.util.Resource
import com.example.manshatsoultancommunity.util.isInternetAvailable
import com.example.manshatsoultancommunity.util.loadImageData
import javax.inject.Inject

class AdvertisementsRepository @Inject constructor(
    private val advertisementsDataSourceRemote : IAdvertisementsDataSourceRemote,
    private val advertisementsDataSourceLocal: IAdvertisementsLocalDataSource,
    private val context :Context
) : IAdvertisementsRepository {
    override suspend fun getAdsPost(): Resource<List<Advertisements>> {
        return if (isInternetAvailable(context)) {
            try {
                advertisementsDataSourceLocal.deleteAdvertisements()
                val remoteAdvertisementsList = advertisementsDataSourceRemote.getAdvertisements()
                val dataListEntity = remoteAdvertisementsList.data?.map {
                    val imageData = loadImageData(it.imageOfAdvertisement!!, context)
                    it.toRoomEntity(imageData)
                } ?: emptyList()

                advertisementsDataSourceLocal.insertAdvertisements(dataListEntity)
                remoteAdvertisementsList
            }catch (e: Exception){
                Log.e("AdvertisementsRepository", "Error fetching remote data: ${e.message}")
                Resource.Error("Error fetching remote data")
            }

        } else {
            val localAdvertisements = advertisementsDataSourceLocal.getAllAdvertisements()
             return if (localAdvertisements.isNotEmpty()) {
                 Log.i("AdvertisementsRepository", localAdvertisements.toString())
                 val dataListDomain = localAdvertisements.map {
                     it.toDomainModel()
                 }
                 Resource.Success(dataListDomain)
             } else {
                 Resource.Error("Empty List cached on your phone!")
             }
        }
    }
}