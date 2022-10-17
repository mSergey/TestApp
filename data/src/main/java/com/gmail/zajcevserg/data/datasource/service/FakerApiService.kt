package com.gmail.zajcevserg.data.datasource.service

import retrofit2.Response
import retrofit2.http.GET


interface FakerApiService {
    @GET("images?_quantity=17&_type=any&_height=500&_width=500")
    suspend fun getData(): Response<FakerResponse>
}