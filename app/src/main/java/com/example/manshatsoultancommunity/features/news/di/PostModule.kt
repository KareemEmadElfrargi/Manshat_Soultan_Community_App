package com.example.manshatsoultancommunity.features.news.di

import android.content.Context
import com.example.manshatsoultancommunity.database.AppDatabase
import com.example.manshatsoultancommunity.features.news.data.data_source.local.IPostsDao
import com.example.manshatsoultancommunity.features.news.data.data_source.local.IPostsLocalDataSource
import com.example.manshatsoultancommunity.features.news.data.data_source.local.PostsLocalDataSource
import com.example.manshatsoultancommunity.features.news.data.data_source.remote.IPostDataSourceRemote
import com.example.manshatsoultancommunity.features.news.data.data_source.remote.PostDataSourceRemote
import com.example.manshatsoultancommunity.features.news.data.repo_impl.PostRepositoryImp
import com.example.manshatsoultancommunity.features.news.domain.repo.IPostRepository
import com.example.manshatsoultancommunity.features.news.domain.usecase.GetEducationUseCase
import com.example.manshatsoultancommunity.features.news.domain.usecase.GetGeneralUseCase
import com.example.manshatsoultancommunity.features.news.domain.usecase.GetPostsUseCase
import com.example.manshatsoultancommunity.features.news.domain.usecase.GetRipUseCase
import com.example.manshatsoultancommunity.features.news.domain.usecase.GetSportUseCase
import com.example.manshatsoultancommunity.features.news.domain.usecase.IGetEducationUseCase
import com.example.manshatsoultancommunity.features.news.domain.usecase.IGetGeneralUseCase
import com.example.manshatsoultancommunity.features.news.domain.usecase.IGetPostsUseCase
import com.example.manshatsoultancommunity.features.news.domain.usecase.IGetRipUseCase
import com.example.manshatsoultancommunity.features.news.domain.usecase.IGetSportUseCase
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PostModule {
    @Provides
    fun providePostDataSourceRemote(remoteDatabase:FirebaseDatabase): IPostDataSourceRemote  = PostDataSourceRemote(remoteDatabase)
    @Provides
    fun providePostLocalDataSource(posDao: IPostsDao): IPostsLocalDataSource = PostsLocalDataSource(posDao)
    @Provides
    fun providePostDao(databaseApp:AppDatabase): IPostsDao = databaseApp.postsDao()
    @Provides
    fun providePostRepo(
        dataSource:IPostDataSourceRemote,
        localDataSource: PostsLocalDataSource,
        @ApplicationContext context: Context
    ): IPostRepository = PostRepositoryImp(dataSource,localDataSource,context)
    @Provides
    fun provideRipPostUseCase(repo:IPostRepository): IGetRipUseCase = GetRipUseCase(repo)

    @Provides
    fun provideSportPostUseCase(repo:IPostRepository): IGetSportUseCase = GetSportUseCase(repo)

    @Provides
    fun provideGeneralPostUseCase(repo:IPostRepository): IGetGeneralUseCase = GetGeneralUseCase(repo)
    @Provides
    fun provideEducationUseCase(repo:IPostRepository): IGetEducationUseCase = GetEducationUseCase(repo)
    @Provides
    fun providePostsUseCase(repo:IPostRepository): IGetPostsUseCase = GetPostsUseCase(repo)


}