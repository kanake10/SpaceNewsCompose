package com.example.spacenews.data.dto

import com.example.spacenews.domain.ArticleDomain

data class ArticleDto(
    val events: List<Any>,
    val featured: Boolean,
    val id: Int,
    val imageUrl: String,
    val launches: List<Launche>,
    val newsSite: String,
    val publishedAt: String,
    val summary: String,
    val title: String,
    val updatedAt: String,
    val url: String
)

fun ArticleDto.toArticleDomain(): ArticleDomain {
    return ArticleDomain(
        id = id,
        imageUrl = imageUrl,
        newsSite = newsSite,
        summary = summary,
        title = title,
        url = url
    )
}