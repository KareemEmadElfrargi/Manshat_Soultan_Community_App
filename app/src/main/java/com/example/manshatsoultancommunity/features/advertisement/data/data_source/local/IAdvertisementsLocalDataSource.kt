package com.example.manshatsoultancommunity.features.advertisement.data.data_source.local

import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.Entity.AdvertisementsEntity


interface IAdvertisementsLocalDataSource {
    suspend fun insertAdvertisements(advertisements:List<AdvertisementsEntity>)
    suspend fun getAllAdvertisements(): List<AdvertisementsEntity>
}