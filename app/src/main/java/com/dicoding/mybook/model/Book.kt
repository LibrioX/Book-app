package com.dicoding.mybook.model

data class Book(
    val id: Long,
    val image: Int,
    val title: String,
    val author: String,
    val description: String,
    val genre: String,
    val rating: Float,
    val price: Int,
    val pages: Int,
    val sell: String,
    val release: String
)