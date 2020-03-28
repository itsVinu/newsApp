package com.example.newsproject.client

import com.example.newsproject.TopHeadlineModel.everythingresponse.EverythingResponse
import com.example.newsproject.TopHeadlineModel.topheadlinebusinessresponse.TopHeadlineBusinessResponse
import com.example.newsproject.TopHeadlineModel.topheadlineentertainmentresponse.TopHeadlineEntertainmentResponse
import com.example.newsproject.TopHeadlineModel.topheadlinehealthresponse.TopHeadlineHealthResponse
import com.example.newsproject.TopHeadlineModel.topheadlinepoliticsresponse.TopHeadlinePoliticsResponse
import com.example.newsproject.TopHeadlineModel.topheadlineresponse.TopHeadlineResponse
import com.example.newsproject.TopHeadlineModel.topheadlinescienceresponse.TopHeadlineScienceResponse
import com.example.newsproject.TopHeadlineModel.topheadlinesportsresponse.TopHeadlineSportsResponse
import com.example.newsproject.TopHeadlineModel.topheadlinetechnologyresponse.TopHeadlineTechnologyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface getresults {

    @GET("top-headlines?country=in&apiKey=87549592e55f41b986f248237a219d90")
    suspend fun getTopHeadlines():Response<TopHeadlineResponse>

    @GET("top-headlines?country=in&apiKey=87549592e55f41b986f248237a219d90&category=sports")
    suspend fun getTopHeadlinesSports():Response<TopHeadlineSportsResponse>

    @GET("top-headlines?country=in&apiKey=87549592e55f41b986f248237a219d90&category=health")
    suspend fun getTopHeadlinesHealth():Response<TopHeadlineHealthResponse>

    @GET("top-headlines?country=in&apiKey=87549592e55f41b986f248237a219d90&category=business")
    suspend fun getTopHeadlinesBusiness():Response<TopHeadlineBusinessResponse>

    @GET("top-headlines?country=in&apiKey=87549592e55f41b986f248237a219d90&category=politics")
    suspend fun getTopHeadlinesPolitics():Response<TopHeadlinePoliticsResponse>

    @GET("top-headlines?country=in&apiKey=87549592e55f41b986f248237a219d90&category=technology")
    suspend fun getTopHeadlinesTechnology():Response<TopHeadlineTechnologyResponse>

    @GET("top-headlines?country=in&apiKey=87549592e55f41b986f248237a219d90&category=science")
    suspend fun getTopHeadlinesScience():Response<TopHeadlineScienceResponse>

    @GET("top-headlines?country=in&apiKey=87549592e55f41b986f248237a219d90&category=entertainment")
    suspend fun getTopHeadlinesEntertainment():Response<TopHeadlineEntertainmentResponse>

    @GET("everything?sortBy=publishedAt")
    suspend fun getEveryHeadlines(@Query("q")q:String,
                                  @Query("apiKey")apiKey:String):Response<EverythingResponse>

}
