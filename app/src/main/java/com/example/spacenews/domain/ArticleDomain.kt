package com.example.spacenews.domain

data class ArticleDomain(
    val id: Int,
    val imageUrl: String,
    val newsSite: String,
    val summary: String,
    val title: String,
    val url: String
)
