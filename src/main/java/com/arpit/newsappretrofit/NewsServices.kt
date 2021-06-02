package com.arpit.newsappretrofit

import com.arpit.newsappretrofit.models.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val Base_Url="https://newsapi.org/"
const val API_KEY="5ffdda713afd443fa1df61fc57cbe6c6"

interface NewsInformation{

     @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country")country:String):Call<News>
}
 object NewsServices{
   val newsInstance: NewsInformation

    init {
    val retrofit = Retrofit.Builder()
        .baseUrl(Base_Url).addConverterFactory(GsonConverterFactory.create())
        .build()

     newsInstance=retrofit.create(NewsInformation::class.java)
         }
}
