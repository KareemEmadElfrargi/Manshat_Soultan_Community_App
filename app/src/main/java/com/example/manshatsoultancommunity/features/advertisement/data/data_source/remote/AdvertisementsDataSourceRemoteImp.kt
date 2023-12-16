package com.example.manshatsoultancommunity.features.advertisement.data.data_source.remote

import android.content.Context
import com.example.manshatsoultancommunity.features.advertisement.data.model.Advertisements
import com.example.manshatsoultancommunity.utils.Constants.CHILD_OF_ADS_REALTIME
import com.example.manshatsoultancommunity.utils.Resource
import com.example.manshatsoultancommunity.utils.isInternetAvailable
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AdvertisementsDataSourceRemoteImp @Inject constructor(
    private val context: Context,
    private val remoteDatabase: FirebaseDatabase
) : IAdvertisementsDataSourceRemote {
    //private var pagingInfo = PagingInfo()
    override suspend fun getAdvertisements(): Resource<List<Advertisements>> {
            val advertisementsList = mutableListOf<Advertisements>()
            return try {
                val databaseRef = remoteDatabase.reference.child(CHILD_OF_ADS_REALTIME).get().await()
                for (objectOfAdvertisements in databaseRef.children) {

                    if (objectOfAdvertisements.value is Map<*, *>) {
                        val advertisement = objectOfAdvertisements.getValue(Advertisements::class.java)
                        advertisement?.let {advertisementsList.add(advertisement)}
                    }
                }
                Resource.Success(advertisementsList)

            } catch (exception: Exception) {
                Resource.Error(exception.message.toString())
            }
        }

}
