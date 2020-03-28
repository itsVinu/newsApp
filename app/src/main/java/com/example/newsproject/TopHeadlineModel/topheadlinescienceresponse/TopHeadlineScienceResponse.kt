package com.example.newsproject.TopHeadlineModel.topheadlinescienceresponse

import com.google.gson.annotations.SerializedName

data class TopHeadlineScienceResponse(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>? = null,

	@field:SerializedName("status")
	val status: String? = null
)