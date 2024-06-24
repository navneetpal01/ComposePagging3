package com.example.composepagging3.data.remote

import com.example.composepagging3.domain.BeerDto
import retrofit2.http.GET
import retrofit2.http.Query


interface BeerApi{




    @GET("beers")
    suspend fun getBeers(
        @Query("page") page : Int,
        @Query("per_page") pageCount : Int
    ) : List<BeerDto>


    companion object{
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }

}