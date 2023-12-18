package com.example.manshatsoultancommunity.features.news.domain.usecase

import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.features.news.data.repo_impl.PostRepositoryImp
import com.example.manshatsoultancommunity.features.news.domain.repo.IPostRepository
import com.example.manshatsoultancommunity.util.Constants.CATEGORY_TYPE_EDUCATION_POST
import com.example.manshatsoultancommunity.util.Constants.CATEGORY_TYPE_RIP_POST
import com.example.manshatsoultancommunity.util.Resource
import javax.inject.Inject

class GetEducationUseCase @Inject constructor(
    private val postRepository: IPostRepository
):IGetEducationUseCase {
    override suspend fun getEducationPost(): Resource<List<Post>> {
        val posts = postRepository.getPost()
        return if (posts is Resource.Success){
            val postsData = posts.data
            val postEducation =  postsData?.filter { post ->
                post.categoryType == CATEGORY_TYPE_EDUCATION_POST
            }
             Resource.Success(postEducation!!)
        } else {
            posts
        }

    }
}