package com.example.manshatsoultancommunity.features.advertisement.di

import android.content.Context
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
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AdsPostModule {
    @Provides
    fun provideAdsPostDataSource(db:FirebaseDatabase,@ApplicationContext context: Context): IAdsPostDataSource  = AdsPostDataSourceImp(context,db)
    @Provides
    fun provideAdsPostRepo(dataSource:IAdsPostDataSource,
                           localDataSource: AdsPostLocalDataSource,
                           @ApplicationContext context: Context):
            IAdsPostRepository = AdsPostRepository(dataSource,localDataSource,context)
    @Provides
    fun provideAdsPostUseCase(repo:IAdsPostRepository): IGetAdsPostUseCase = GetAdsPostUseCase(repo)
    @Provides
    fun provideAdsPostDao(database:AppDatabase): AdsPostDao = database.adsPostDao()
    @Provides
    fun provideAdsPostLocalDataSource(adsPostDao:AdsPostDao): IAdsPostLocalDataSource = AdsPostLocalDataSource(adsPostDao)


}