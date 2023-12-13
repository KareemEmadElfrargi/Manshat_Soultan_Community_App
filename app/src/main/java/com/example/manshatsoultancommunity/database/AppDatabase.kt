package com.example.manshatsoultancommunity.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.AdsPostDao
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.AdsPostEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@Database(entities = [AdsPostEntity::class], version = 1)
abstract class AppDatabase :RoomDatabase() {
    abstract fun adsPostDao():AdsPostDao

}
