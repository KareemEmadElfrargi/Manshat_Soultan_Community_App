package com.example.manshatsoultancommunity.features.advertisement.di

import android.content.Context
import com.example.manshatsoultancommunity.database.AppDatabase
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.IAdvertisementsDao
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.AdvertisementsLocalDataSource
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.IAdvertisementsLocalDataSource
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.remote.AdvertisementsDataSourceRemoteImp
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.remote.IAdvertisementsDataSourceRemote
import com.example.manshatsoultancommunity.features.advertisement.data.repo_impl.AdvertisementsRepository
import com.example.manshatsoultancommunity.features.advertisement.domain.repo.IAdvertisementsRepository
import com.example.manshatsoultancommunity.features.advertisement.domain.usecase.GetAdvertisementsUseCase
import com.example.manshatsoultancommunity.features.advertisement.domain.usecase.IGetAdvertisementsUseCase
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AdvertisementsModule {
    @Provides
    fun provideAdvertisementsDataSourceRemote(remoteDatabase:FirebaseDatabase, @ApplicationContext context: Context): IAdvertisementsDataSourceRemote  = AdvertisementsDataSourceRemoteImp(context,remoteDatabase)
    @Provides
    fun provideAdvertisementsLocalDataSource(advertisementsDao:IAdvertisementsDao): IAdvertisementsLocalDataSource = AdvertisementsLocalDataSource(advertisementsDao)
    @Provides
    fun provideAdvertisementsDao(databaseApp:AppDatabase): IAdvertisementsDao = databaseApp.advertisementsDao()
    @Provides
    fun provideAdvertisementsRepo(dataSource:IAdvertisementsDataSourceRemote,
                                  localDataSource: IAdvertisementsLocalDataSource,
                                  @ApplicationContext context: Context): IAdvertisementsRepository = AdvertisementsRepository(dataSource,localDataSource,context)
    @Provides
    fun provideAdvertisementsUseCase(repo:IAdvertisementsRepository): IGetAdvertisementsUseCase = GetAdvertisementsUseCase(repo)



}