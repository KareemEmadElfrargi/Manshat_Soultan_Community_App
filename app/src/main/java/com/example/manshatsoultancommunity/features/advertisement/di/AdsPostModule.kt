package com.example.manshatsoultancommunity.features.advertisement.di

import com.example.manshatsoultancommunity.features.advertisement.data.data_source.remote.AdsPostDataSourceImp
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.remote.IAdsPostDataSource
import com.example.manshatsoultancommunity.features.advertisement.data.repo_impl.AdsPostRepository
import com.example.manshatsoultancommunity.features.advertisement.domain.repo.IAdsPostRepository
import com.example.manshatsoultancommunity.features.advertisement.domain.usecase.GetAdsPostUseCase
import com.example.manshatsoultancommunity.features.advertisement.domain.usecase.IGetAdsPostUseCase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AdsPostModule {
    @Provides
    fun provideAdsPostDataSource(db:FirebaseDatabase): IAdsPostDataSource  = AdsPostDataSourceImp(db)
    @Provides
    fun provideAdsPostRepo(dataSource:IAdsPostDataSource): IAdsPostRepository = AdsPostRepository(dataSource)
    @Provides
    fun provideAdsPostUseCase(repo:IAdsPostRepository): IGetAdsPostUseCase = GetAdsPostUseCase(repo)
}