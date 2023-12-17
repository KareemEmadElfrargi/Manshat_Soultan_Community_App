package com.example.manshatsoultancommunity.features.advertisement.data.data_source.local

import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.Entity.AdvertisementsEntity
import javax.inject.Inject

class AdvertisementsLocalDataSource @Inject constructor(
    private val advertisementsDao: IAdvertisementsDao
) :IAdvertisementsLocalDataSource{
    override suspend fun insertAdvertisements(advertisements: List<AdvertisementsEntity>) {
        advertisementsDao.insertAdvertisements(advertisements)
    }

    override suspend fun getAllAdvertisements(): List<AdvertisementsEntity> {
        return advertisementsDao.getAllAdvertisements()
    }

    override suspend fun deleteAdvertisements() {
        advertisementsDao.deleteAdvertisements()
    }

}