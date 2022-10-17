package com.gmail.zajcevserg.fakerapi.di

import com.gmail.zajcevserg.fakerapi.presentation.fragment.menu.MenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelsModule = module {
    viewModel<MenuViewModel> {
        MenuViewModel(
            getFakerDataRemoteUseCase = get(),
            getFakerDataLocalUseCase = get(),
            saveFakerDataLocalUseCase = get()
        )
    }
}