package com.example.spacenews.domain

import javax.inject.Inject
import com.example.spacenews.core.Resource
import com.example.spacenews.data.dto.toArticleDetailDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetArticleDetailUseCase @Inject constructor(
    private val repository: SpaceNewsRepo
) {
    operator fun invoke(id: Int): Flow<Resource<ArticleDetailDomain>> = flow {
        try {
            emit(Resource.Loading<ArticleDetailDomain>())
            val exchange = repository.getArticleDetails(id).toArticleDetailDomain()
            emit(Resource.Success<ArticleDetailDomain>(exchange))
        } catch(e: HttpException) {
            emit(Resource.Error<ArticleDetailDomain>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<ArticleDetailDomain>("Couldn't reach server. Check your internet connection."))
        }
    }
}