package com.example.manshatsoultancommunity.features.news.presentation.common.adapter

import android.view.View
import com.example.manshatsoultancommunity.features.news.data.model.Post

interface InteractionWithOptionPost {
    fun onClickOptionPopupMenu(post: Post, view: View)
}