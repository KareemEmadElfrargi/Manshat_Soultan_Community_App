package com.example.manshatsoultancommunity.features.news.data.model

data class PostByImage (
    private val nameOfPublisher :String,
    private val imageOfPublisher :String?=null,
    private val timeOfPost :String,
    private val content :String,
    private val imageOfPost :String?=null
    )