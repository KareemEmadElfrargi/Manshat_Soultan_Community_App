package com.example.manshatsoultancommunity.features.news.domain.usecase

import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.features.news.domain.repo.IPostRepository
import com.example.manshatsoultancommunity.util.Constants.CATEGORY_TYPE_SPORT_POST
import com.example.manshatsoultancommunity.util.Resource
import javax.inject.Inject

class GetSportUseCase@Inject constructor(
    private val postRepository: IPostRepository
):IGetSportUseCase {
    override suspend fun getSportPost(): Resource<List<Post>> {
        val posts = postRepository.getPost()
        return if (posts is Resource.Success){
            val postsData = posts.data
            val postSport =  postsData?.filter { post ->
                post.categoryType == CATEGORY_TYPE_SPORT_POST
            }
            Resource.Success(postSport!!)
        } else {
            posts
        }

    }
}