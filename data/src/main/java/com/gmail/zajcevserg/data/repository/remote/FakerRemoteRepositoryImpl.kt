package com.gmail.zajcevserg.data.repository.remote

import com.gmail.zajcevserg.data.datasource.service.FakerApiService
import com.gmail.zajcevserg.data.datasource.service.FakerResponse
import com.gmail.zajcevserg.domain.repository.FakerDataRemoteRepository
import com.gmail.zajcevserg.domain.uimodels.FakerUiModel


class FakerRemoteRepositoryImpl(
    private val datasource: FakerApiService
) : FakerDataRemoteRepository {
    override suspend fun getData(): List<FakerUiModel> {
        val data = datasource.getData()
        return if (data.isSuccessful && data.body() != null) {
            data.body()!!.mapToUi()
        } else emptyList<FakerUiModel>()
    }

    private fun FakerResponse.mapToUi(): List<FakerUiModel> {
        val uiModels = mutableListOf<FakerUiModel>()
        data.forEach {
            val uiModel = FakerUiModel(
                title = it.title,
                description = it.description,
                url = it.url
            )
            uiModels.add(uiModel)
        }
        return uiModels
    }

}