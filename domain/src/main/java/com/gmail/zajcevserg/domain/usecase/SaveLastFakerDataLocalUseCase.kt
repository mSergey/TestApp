package com.gmail.zajcevserg.domain.usecase

import com.gmail.zajcevserg.domain.repository.FakerDataLocalRepository
import com.gmail.zajcevserg.domain.uimodels.FakerUiModel

class SaveLastFakerDataLocalUseCase(
    private val fakerDataLocalRepository: FakerDataLocalRepository
) {
    suspend fun execute(data: List<FakerUiModel>) {
        fakerDataLocalRepository.saveData(data)
    }
}