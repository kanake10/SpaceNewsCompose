package com.example.spacenews.data.dto

import com.example.spacenews.domain.ArticleDetailDomain

data class ArticleDetailDto(
    val events: List<Any>,
    val featured: Boolean,
    val id: Int,
    val imageUrl: String,
    val launches: List<Any>,
    val newsSite: String,
    val publishedAt: String,
    val summary: String,
    val title: String,
    val updatedAt: String,
    val url: String
)

fun ArticleDetailDto.toArticleDetailDomain(): ArticleDetailDomain {
    return ArticleDetailDomain(
        imageUrl = imageUrl,
        newsSite = newsSite,
        summary = summary,
        title = title,
        url = url
    )
}