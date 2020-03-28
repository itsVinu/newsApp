package com.example.newsproject.TopHeadlineModel.topheadlinebusinessresponse

import com.google.gson.annotations.SerializedName

data class TopHeadlineBusinessResponse(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>? = null,

	@field:SerializedName("status")
	val status: String? = null
)