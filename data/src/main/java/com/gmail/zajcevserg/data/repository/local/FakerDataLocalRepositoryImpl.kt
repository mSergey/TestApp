package com.gmail.zajcevserg.data.repository.local

import com.gmail.zajcevserg.data.datasource.database.FakerDataDao
import com.gmail.zajcevserg.data.datasource.database.FakerDataEntity
import com.gmail.zajcevserg.domain.repository.FakerDataLocalRepository
import com.gmail.zajcevserg.domain.uimodels.FakerUiModel


class FakerDataLocalRepositoryImpl(
    private val datasource: FakerDataDao
) : FakerDataLocalRepository {
    override suspend fun getData(): List<FakerUiModel> {
        return datasource.getData().mapToUi()
    }

    override suspend fun saveData(data: List<FakerUiModel>) {
        datasource.updateLastSaved(data = data.mapToDatabase())
    }

    private fun List<FakerDataEntity>.mapToUi(): List<FakerUiModel> {
        val uiModels = mutableListOf<FakerUiModel>()
        forEach {
            val uiModel = FakerUiModel(
                title = it.title,
                description = it.description,
                url = it.url
            )
            uiModels.add(uiModel)
        }
        return uiModels
    }

    private fun List<FakerUiModel>.mapToDatabase(): List<FakerDataEntity> {
        val dbEntities = mutableListOf<FakerDataEntity>()
        forEach {
            val dbEntity = FakerDataEntity(
                id = 0,
                title = it.title,
                description = it.description,
                url = it.url
            )
            dbEntities.add(dbEntity)
        }
        return dbEntities
    }

}
