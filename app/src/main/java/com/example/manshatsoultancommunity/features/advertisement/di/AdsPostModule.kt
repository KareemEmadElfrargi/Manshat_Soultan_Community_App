package com.example.manshatsoultancommunity.features.advertisement.di

import com.example.manshatsoultancommunity.database.AppDatabase
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.AdsPostDao
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.AdsPostLocalDataSource
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.IAdsPostLocalDataSource
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.remote.AdsPostDataSourceImp
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.remote.IAdsPostDataSource
import com.example.manshatsoultancommunity.features.advertisement.data.repo_impl.AdsPostRepository
import com.example.manshatsoultancommunity.features.advertisement.domain.repo.IAdsPostRepository
import com.example.manshatsoultancommunity.features.advertisement.domain.usecase.GetAdsPostUseCase
import com.example.manshatsoultancommunity.features.advertisement.domain.usecase.IGetAdsPostUseCase
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AdsPostModule {
    @Provides
    fun provideAdsPostDataSource(db:FirebaseDatabase): IAdsPostDataSource  = AdsPostDataSourceImp(db)
    @Provides
    fun provideAdsPostRepo(dataSource:IAdsPostDataSource,localDataSource: AdsPostLocalDataSource): IAdsPostRepository = AdsPostRepository(dataSource,localDataSource)
    @Provides
    fun provideAdsPostUseCase(repo:IAdsPostRepository): IGetAdsPostUseCase = GetAdsPostUseCase(repo)
    @Provides
    fun provideAdsPostDao(database:AppDatabase): AdsPostDao = database.adsPostDao()
    @Provides
    fun provideAdsPostLocalDataSource(adsPostDao:AdsPostDao): IAdsPostLocalDataSource = AdsPostLocalDataSource(adsPostDao)


}