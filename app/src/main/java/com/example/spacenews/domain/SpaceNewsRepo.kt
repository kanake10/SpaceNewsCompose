package com.example.spacenews.domain

import com.example.spacenews.data.dto.ArticleDetailDto
import com.example.spacenews.data.dto.ArticleDto

interface SpaceNewsRepo {
    suspend fun getArticles(limit:Int):List<ArticleDto>
    suspend fun getArticleDetails(id : Int): ArticleDetailDto
}