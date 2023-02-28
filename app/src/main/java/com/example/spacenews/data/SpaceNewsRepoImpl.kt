package com.example.spacenews.data

import com.example.spacenews.data.dto.ArticleDetailDto
import com.example.spacenews.data.dto.ArticleDto
import com.example.spacenews.domain.SpaceNewsRepo
import javax.inject.Inject

class SpaceNewsRepoImpl @Inject constructor(
    private val spaceNewsApi: SpaceNewsApi
) : SpaceNewsRepo {
    override suspend fun getArticles(limit:Int): List<ArticleDto> {
        return spaceNewsApi.getArticles(limit)
    }

    override suspend fun getArticleDetails(id: Int): ArticleDetailDto {
        return spaceNewsApi.getArticleDetails(id)
    }
}

