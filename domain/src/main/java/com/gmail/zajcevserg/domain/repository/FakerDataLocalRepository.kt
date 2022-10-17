package com.gmail.zajcevserg.domain.repository

import com.gmail.zajcevserg.domain.uimodels.FakerUiModel

interface FakerDataLocalRepository {
    suspend fun getData(): List<FakerUiModel>
    suspend fun saveData(data: List<FakerUiModel>)
}