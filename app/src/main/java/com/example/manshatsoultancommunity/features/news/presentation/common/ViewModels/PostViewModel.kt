package com.example.manshatsoultancommunity.features.news.presentation.common.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manshatsoultancommunity.features.advertisement.data.model.Advertisements
import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.features.news.domain.usecase.IGetEducationUseCase
import com.example.manshatsoultancommunity.features.news.domain.usecase.IGetGeneralUseCase
import com.example.manshatsoultancommunity.features.news.domain.usecase.IGetRipUseCase
import com.example.manshatsoultancommunity.features.news.domain.usecase.IGetSportUseCase
import com.example.manshatsoultancommunity.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val ripUseCase: IGetRipUseCase,
    private val sportUseCase: IGetSportUseCase,
    private val generalUseCase: IGetGeneralUseCase,
    private val educationUseCase: IGetEducationUseCase,
):ViewModel() {

    private val _ripPostList = MutableStateFlow<Resource<List<Post>>>(Resource.Unspecified())
    val ripPostList : StateFlow<Resource<List<Post>>> = _ripPostList

    private val _sportPostList = MutableStateFlow<Resource<List<Post>>>(Resource.Unspecified())
    val sportPostList : StateFlow<Resource<List<Post>>> = _sportPostList

    private val _generalPostList = MutableStateFlow<Resource<List<Post>>>(Resource.Unspecified())
    val generalPostList : StateFlow<Resource<List<Post>>> = _generalPostList

    private val _educationPostList = MutableStateFlow<Resource<List<Post>>>(Resource.Unspecified())
    val educationPostList : StateFlow<Resource<List<Post>>> = _educationPostList


    init {
        fetchRipPost()
        fetchSportPost()
        fetchGeneralPost()
        fetchEducationPost()
        fetchPosts()
    }
    fun fetchRipPost() {
        viewModelScope.launch {
            try {
                _ripPostList.emit(Resource.Loading())

                val result = withContext(Dispatchers.IO) {
                    ripUseCase.getRipPost()
                }

                _ripPostList.emit(result)
            } catch (e: Exception) {
                _ripPostList.emit(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }
    fun fetchSportPost(){
        viewModelScope.launch {
            _sportPostList.emit(Resource.Loading())
        }
        viewModelScope.launch {
            val result = sportUseCase.getSportPost()
            _sportPostList.emit(result)
        }
    }
    fun fetchGeneralPost(){
        viewModelScope.launch {
            try {
                _generalPostList.emit(Resource.Loading())
                val result = withContext(Dispatchers.IO) {
                    generalUseCase.getGeneralPost()
                }
                _generalPostList.emit(result)
            } catch (e: Exception) {
                _generalPostList.emit(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }

    fun fetchEducationPost(){
        viewModelScope.launch {
            try {
                _educationPostList.emit(Resource.Loading())
                val result = withContext(Dispatchers.IO) {
                    educationUseCase.getEducationPost()
                }
                _educationPostList.emit(result)
            } catch (e: Exception) {
                _educationPostList.emit(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }
    fun fetchPosts(){
        viewModelScope.launch {
            _generalPostList.emit(Resource.Loading())
        }
        viewModelScope.launch {
            val result = generalUseCase.getGeneralPost()
            _generalPostList.emit(result)
        }
    }
}