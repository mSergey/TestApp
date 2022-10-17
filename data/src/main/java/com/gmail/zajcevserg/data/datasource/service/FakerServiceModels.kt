package com.gmail.zajcevserg.data.datasource.service

import com.google.gson.annotations.SerializedName

data class FakerResponse(
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("code")
    val code: Int,
    @field:SerializedName("total")
    val total: Int,
    @field:SerializedName("data")
    val data: List<FakerData>
)


data class FakerData(
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("url")
    val url: String
)