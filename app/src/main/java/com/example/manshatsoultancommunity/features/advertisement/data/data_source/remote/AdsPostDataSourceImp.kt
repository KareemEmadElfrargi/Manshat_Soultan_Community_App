package com.example.manshatsoultancommunity.features.advertisement.data.data_source.remote

import com.example.manshatsoultancommunity.features.advertisement.data.model.AnnouncementPost
import com.example.manshatsoultancommunity.utils.Constants.CHILD_OF_ADS_REALTIME
import com.example.manshatsoultancommunity.utils.Resource
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AdsPostDataSourceImp @Inject constructor(
    private val database : FirebaseDatabase
)
    :IAdsPostDataSource {
    override suspend fun getAdsPost(): Resource<List<AnnouncementPost>> {
        val listOfAds = mutableListOf<AnnouncementPost>()
        return try {
            val databaseRef = database.reference.child(CHILD_OF_ADS_REALTIME).get().await()

            for (objectOFAnnouncementPost in databaseRef.children) {
                if (objectOFAnnouncementPost.value is Map<*, *>) {
                    val postAds = objectOFAnnouncementPost.getValue(AnnouncementPost::class.java)
                    postAds?.let {
                        listOfAds.add(postAds)
                    }
                }
            }
            Resource.Success(listOfAds)
        } catch (exception: Exception) {
            Resource.Error(exception.message.toString())
        }
    }

}