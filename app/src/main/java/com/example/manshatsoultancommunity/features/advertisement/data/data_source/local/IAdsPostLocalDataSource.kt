package com.example.manshatsoultancommunity.features.advertisement.data.data_source.local



interface IAdsPostLocalDataSource {
    suspend fun insertAdsPost(adsPosts:List<AdsPostEntity>)
    suspend fun getAllAdsPosts(): List<AdsPostEntity>
}