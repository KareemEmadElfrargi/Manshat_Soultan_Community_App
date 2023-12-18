package com.example.manshatsoultancommunity.features.news.domain.usecase

import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.features.news.data.repo_impl.PostRepositoryImp
import com.example.manshatsoultancommunity.features.news.domain.repo.IPostRepository
import com.example.manshatsoultancommunity.util.Constants.CATEGORY_TYPE_RIP_POST
import com.example.manshatsoultancommunity.util.Resource
import javax.inject.Inject

class GetRipUseCase @Inject constructor(
    private val postRepository: IPostRepository
):IGetRipUseCase {
    override suspend fun getRipPost(): Resource<List<Post>> {
        val posts = postRepository.getPost()
        return if (posts is Resource.Success){
            val postsData = posts.data
            val postRip =  postsData?.filter { post ->
                post.categoryType == CATEGORY_TYPE_RIP_POST
            }
             Resource.Success(postRip!!)
        } else {
            posts
        }

    }
}