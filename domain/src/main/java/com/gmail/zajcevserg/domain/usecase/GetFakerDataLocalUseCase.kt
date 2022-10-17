package com.gmail.zajcevserg.domain.usecase

import com.gmail.zajcevserg.domain.repository.FakerDataLocalRepository
import com.gmail.zajcevserg.domain.uimodels.FakerUiModel

class GetFakerDataLocalUseCase(
    private val fakerDataLocalRepository: FakerDataLocalRepository
) {
    suspend fun execute(): List<FakerUiModel> {
        return fakerDataLocalRepository.getData()
    }
}