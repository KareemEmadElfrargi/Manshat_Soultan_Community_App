package com.example.manshatsoultancommunity.features.advertisement.data.data_source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.Entity.AdvertisementsEntity

@Dao
interface IAdvertisementsDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdvertisements(advertisements:List<AdvertisementsEntity>)

    @Query("DELETE FROM AnnouncementsTable")
    suspend fun deleteAdvertisements()

    @Query("SELECT * FROM AnnouncementsTable")
    suspend fun getAllAdvertisements(): List<AdvertisementsEntity>

}