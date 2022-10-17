package com.gmail.zajcevserg.fakerapi.di

import com.gmail.zajcevserg.domain.usecase.GetFakerDataLocalUseCase
import com.gmail.zajcevserg.domain.usecase.GetFakerDataRemoteUseCase
import com.gmail.zajcevserg.domain.usecase.SaveLastFakerDataLocalUseCase
import org.koin.dsl.module


val useCaseModule = module {
    factory<GetFakerDataRemoteUseCase> {
        GetFakerDataRemoteUseCase(fakerRemoteRepository = get())
    }

    factory<SaveLastFakerDataLocalUseCase> {
        SaveLastFakerDataLocalUseCase(fakerDataLocalRepository = get())
    }

    factory<GetFakerDataLocalUseCase> {
        GetFakerDataLocalUseCase(fakerDataLocalRepository = get())
    }

}