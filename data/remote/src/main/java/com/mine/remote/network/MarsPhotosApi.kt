package com.mine.remote.network

import com.mine.model.MarsPhotosResponse
import com.mine.utils.Constants.KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsPhotosApi {
    @GET("rovers/curiosity/photos")
    suspend fun fetchPhotos(
        @Query("sol") sol: Int = 1000,
        @Query("api_key") apiKey: String = KEY
    ): Response<MarsPhotosResponse>
}