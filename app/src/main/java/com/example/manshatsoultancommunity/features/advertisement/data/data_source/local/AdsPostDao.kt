package com.example.manshatsoultancommunity.features.advertisement.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AdsPostDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdsPost(adsPosts:List<AdsPostEntity>)

    @Query("SELECT * FROM AdsPostTable")
    suspend fun getAllAdsPosts(): List<AdsPostEntity>

}