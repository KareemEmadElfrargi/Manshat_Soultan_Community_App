package com.example.manshatsoultancommunity.features.advertisement.presentation.common.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manshatsoultancommunity.features.advertisement.data.model.AnnouncementPost
import com.example.manshatsoultancommunity.features.advertisement.domain.usecase.IGetAdsPostUseCase
import com.example.manshatsoultancommunity.features.advertisement.presentation.common.adapter.InteractionWithAds
import com.example.manshatsoultancommunity.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnouncementPostViewModel @Inject constructor(
    private val getAdsPostUseCase: IGetAdsPostUseCase,
) :ViewModel() {
    private val _adsPostList = MutableStateFlow<Resource<List<AnnouncementPost>>>(Resource.Unspecified())
    val adsPostList :StateFlow<Resource<List<AnnouncementPost>>> = _adsPostList
    private val _adsPostClicked = MutableStateFlow<AnnouncementPost>(AnnouncementPost())
    val adsPostClicked :StateFlow<AnnouncementPost> = _adsPostClicked

    init {
        fetchAdsPosts()
    }

    private fun fetchAdsPosts(){
        viewModelScope.launch {
            _adsPostList.emit(Resource.Loading())
        }

        viewModelScope.launch {
            val result = getAdsPostUseCase.getAdsPostUseCase()
            _adsPostList.emit(result)
        }


    }

}