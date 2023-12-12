package com.example.manshatsoultancommunity.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.AdsPostDao
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.AdsPostEntity

@Database(entities = [AdsPostEntity::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun adsPostDao():AdsPostDao

//    companion object {
//        @Volatile
//        private var instance: AppDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: createDatabase(context).also { instance = it }
//        }
//        private fun createDatabase(context: Context) =
//            Room.databaseBuilder(context.applicationContext,AppDatabase::class.java, "Manshat_Soultan.db").build()
//    }

}
