package com.arpit.newsappretrofit.models

data class News(val totalResults: Int, val articles: ArrayList<Article>)

data class Article(
        val source: Source,
        val author: String,
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String
)


data class Source(val name: String)