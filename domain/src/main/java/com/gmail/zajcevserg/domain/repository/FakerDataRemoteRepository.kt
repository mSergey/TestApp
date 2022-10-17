package com.gmail.zajcevserg.domain.repository

import com.gmail.zajcevserg.domain.uimodels.FakerUiModel

interface FakerDataRemoteRepository {
    suspend fun getData(): List<FakerUiModel>
}