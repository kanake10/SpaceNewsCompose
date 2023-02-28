package com.example.spacenews.domain

import com.example.spacenews.core.Resource
import com.example.spacenews.data.dto.toArticleDomain
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetArticlesUseCase @Inject constructor(
    private val repository: SpaceNewsRepo
) {
    operator fun invoke(limit:Int): Flow<Resource<List<ArticleDomain>>> = flow {
        try {
            emit(Resource.Loading<List<ArticleDomain>>())
            val articles = repository.getArticles(limit).map { it.toArticleDomain() }
            emit(Resource.Success<List<ArticleDomain>>(articles))
        } catch(e: HttpException) {
            emit(Resource.Error<List<ArticleDomain>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<ArticleDomain>>("Couldn't reach server. Check your internet connection."))
        }
    }
}