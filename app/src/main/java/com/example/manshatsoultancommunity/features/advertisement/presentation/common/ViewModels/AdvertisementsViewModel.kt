package com.example.manshatsoultancommunity.features.advertisement.presentation.common.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manshatsoultancommunity.features.advertisement.data.model.Advertisements
import com.example.manshatsoultancommunity.features.advertisement.domain.usecase.IGetAdvertisementsUseCase
import com.example.manshatsoultancommunity.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdvertisementsViewModel @Inject constructor(
    private val getAdvertisementsUseCase: IGetAdvertisementsUseCase,
) :ViewModel() {
    private val _advertisementsList = MutableStateFlow<Resource<List<Advertisements>>>(Resource.Unspecified())
    val advertisementsList :StateFlow<Resource<List<Advertisements>>> = _advertisementsList

    init {
        fetchAdvertisements()

    }
     fun fetchAdvertisements(){
        viewModelScope.launch {
            _advertisementsList.emit(Resource.Loading())
        }
        viewModelScope.launch {
            val result = getAdvertisementsUseCase.getAdvertisementsUseCase()
            _advertisementsList.emit(result)
        }
    }
}
