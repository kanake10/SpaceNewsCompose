package com.example.spacenews.domain

data class ArticleDetailDomain(
    val imageUrl: String,
    val newsSite: String,
    val summary: String,
    val title: String,
    val url: String
)
