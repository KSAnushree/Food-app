package com.example.foodmenuapp

import retrofit2.http.*
import retrofit2.Response

//hi

interface FoodApiService {
    @GET("/foods")
    suspend fun getFoods(): List<FoodItem>

    @POST("/foods")
    suspend fun createFood(@Body food: FoodItem): FoodItem

    @PUT("/foods/{id}")
    suspend fun updateFood(@Path("id") id: Int, @Body food: FoodItem): FoodItem

    @DELETE("/foods/{id}")
    suspend fun deleteFood(@Path("id") id: Int): Response<Unit>
}