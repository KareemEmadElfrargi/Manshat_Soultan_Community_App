package com.example.manshatsoultancommunity.features.advertisement.data.repo_impl

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.bumptech.glide.Glide
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.AdsPostLocalDataSource
import com.example.manshatsoultancommunity.features.advertisement.data.data_source.remote.IAdsPostDataSource
import com.example.manshatsoultancommunity.features.advertisement.data.mapper.toDomainModel
import com.example.manshatsoultancommunity.features.advertisement.data.mapper.toRoomEntity
import com.example.manshatsoultancommunity.features.advertisement.data.model.AnnouncementPost
import com.example.manshatsoultancommunity.features.advertisement.domain.repo.IAdsPostRepository
import com.example.manshatsoultancommunity.utils.Resource
import com.google.api.ResourceProto.resource
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class AdsPostRepository @Inject constructor(
    private val adsPostDataSource : IAdsPostDataSource,
    private val dataSource: AdsPostLocalDataSource,
    private val context :Context
) : IAdsPostRepository {
    override suspend fun getAdsPost(): Resource<List<AnnouncementPost>> {

        val remoteAdsPost = adsPostDataSource.getAdsPost()
        return if (remoteAdsPost is Resource.Success) {

            dataSource.insertAdsPost(
            remoteAdsPost.data?.map {
                val imageData = loadImageData(it.imageAnnouncement!!)
                Log.i("AdsPostRepositoryxxxxx",imageData.toString())
                    it.toRoomEntity(imageData)
                }!!
            )
            remoteAdsPost

        } else {
            val localAdsPost = dataSource.getAllAdsPosts()
            return if (localAdsPost.isNotEmpty()) {
                Resource.Success(localAdsPost.map {
                    it.toDomainModel()
                })
            } else {
                Resource.Error(remoteAdsPost.message.toString())
            }
        }

    }

    fun loadImageData(imageUrl: String): ByteArray {
        return try {
            // Use Glide to load the image and retrieve the byte array
            val glideRequest = Glide.with(context)
                .asBitmap()
                .load(imageUrl)

            val bitmap = glideRequest.submit().get()
            // Convert the Bitmap to a ByteArray
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.toByteArray()
        } catch (e: Exception) {
            // Handle errors, such as network issues or image loading failures
            // You might want to log the error or return a default image data
            e.printStackTrace()
            byteArrayOf() // Default empty byte array for simplicity
        }
    }
}