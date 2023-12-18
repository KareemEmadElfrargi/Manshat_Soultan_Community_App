package com.example.manshatsoultancommunity.features.news.domain.usecase

import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.features.news.domain.repo.IPostRepository
import com.example.manshatsoultancommunity.util.Constants.CATEGORY_TYPE_GENERAL_POST
import com.example.manshatsoultancommunity.util.Constants.CATEGORY_TYPE_SPORT_POST
import com.example.manshatsoultancommunity.util.Resource
import javax.inject.Inject

class GetGeneralUseCase@Inject constructor(
    private val postRepository: IPostRepository
):IGetGeneralUseCase {
    override suspend fun getGeneralPost(): Resource<List<Post>> {
        val posts = postRepository.getPost()
        return if (posts is Resource.Success){
            val postsData = posts.data
            val postGeneral =  postsData?.filter { post ->
                post.categoryType == CATEGORY_TYPE_GENERAL_POST
            }
            Resource.Success(postGeneral!!)
        } else {
            posts
        }

    }
}