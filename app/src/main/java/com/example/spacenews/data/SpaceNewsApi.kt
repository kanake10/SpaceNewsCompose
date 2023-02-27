package com.example.spacenews.data

import com.example.spacenews.core.Constants.ARTICLES_DETAILS_ENDPOINT
import com.example.spacenews.core.Constants.ARTICLES_ENDPOINT
import com.example.spacenews.data.dto.ArticleDetailDto
import com.example.spacenews.data.dto.ArticleDto
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceNewsApi {
    @GET(ARTICLES_ENDPOINT)
    suspend fun getArticles():List<ArticleDto>

    @GET(ARTICLES_DETAILS_ENDPOINT)
    suspend fun getArticleDetails(@Path("id") id: Int): ArticleDetailDto
}