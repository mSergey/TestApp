package com.gmail.zajcevserg.domain.usecase

import com.gmail.zajcevserg.domain.repository.FakerDataRemoteRepository
import com.gmail.zajcevserg.domain.uimodels.FakerUiModel

class GetFakerDataRemoteUseCase(private val fakerRemoteRepository: FakerDataRemoteRepository) {
    suspend fun execute(): List<FakerUiModel> {
        return fakerRemoteRepository.getData()
    }
}