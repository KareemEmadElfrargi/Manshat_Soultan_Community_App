package com.example.manshatsoultancommunity.features.advertisement.data.data_source.local

import javax.inject.Inject

class AdsPostLocalDataSource @Inject constructor(
    private val adsPostDao: AdsPostDao
) :IAdsPostLocalDataSource{
    override suspend fun insertAdsPost(adsPosts: List<AdsPostEntity>) {
        adsPostDao.insertAdsPost(adsPosts)
    }

    override suspend fun getAllAdsPosts(): List<AdsPostEntity> {
        return adsPostDao.getAllAdsPosts()
    }

}