package com.example.manshatsoultancommunity.features.advertisement.data.data_source.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.manshatsoultancommunity.features.advertisement.data.model.AnnouncementPost
import com.example.manshatsoultancommunity.utils.Constants.CHILD_OF_ADS_REALTIME
import com.example.manshatsoultancommunity.utils.Resource
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AdsPostDataSourceImp @Inject constructor(
    private val context: Context,
    private val database: FirebaseDatabase
) : IAdsPostDataSource {

    override suspend fun getAdsPost(): Resource<List<AnnouncementPost>> {
        if (!isInternetAvailable()) {
            return Resource.Error("No internet connection")
        }

        val listOfAds = mutableListOf<AnnouncementPost>()
        return try {
            val databaseRef = database.reference.child(CHILD_OF_ADS_REALTIME).get().await()
            for (objectOFAnnouncementPost in databaseRef.children) {
                if (objectOFAnnouncementPost.value is Map<*, *>) {
                    val postAds = objectOFAnnouncementPost.getValue(AnnouncementPost::class.java)
                    postAds?.let {
                        Log.v("AdsPostDataSourceImp", postAds.toString())
                        listOfAds.add(postAds)
                    }
                }
            }
            Resource.Success(listOfAds)
        } catch (exception: Exception) {
            Resource.Error(exception.message.toString())
        }
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities =
            connectivityManager.getNetworkCapabilities(network)
        return capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }
}
