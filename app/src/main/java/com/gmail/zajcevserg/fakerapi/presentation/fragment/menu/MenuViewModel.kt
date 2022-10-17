package com.gmail.zajcevserg.fakerapi.presentation.fragment.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.zajcevserg.domain.result.Result
import com.gmail.zajcevserg.domain.uimodels.FakerUiModel
import com.gmail.zajcevserg.domain.usecase.GetFakerDataLocalUseCase
import com.gmail.zajcevserg.domain.usecase.GetFakerDataRemoteUseCase
import com.gmail.zajcevserg.domain.usecase.SaveLastFakerDataLocalUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MenuViewModel(
    private val getFakerDataRemoteUseCase: GetFakerDataRemoteUseCase,
    private val getFakerDataLocalUseCase: GetFakerDataLocalUseCase,
    private val saveFakerDataLocalUseCase: SaveLastFakerDataLocalUseCase
) : ViewModel() {

    val liveModels: LiveData<Result>
        get() = _liveModels

    private val _liveModels by lazy {
        MutableLiveData<Result>()
    }

    init {

        viewModelScope.launch {

            val modelsDeferred = async {

                try {
                    val data = getFakerDataRemoteUseCase.execute()
                    if (data.isNotEmpty()) {
                        launch {
                            try {
                                saveFakerDataLocalUseCase.execute(data = data)
                            } catch (e: Exception) {
                                if (e is CancellationException) throw e
                            }
                        }
                    }
                    data
                } catch (e: Exception) {
                    if (e is CancellationException) throw e
                    else {
                        _liveModels.value = Result.Error(message = "No internet connection")
                        getFakerDataLocalUseCase.execute()
                    }
                }
            }

            val models = modelsDeferred.await()
            if (models.isNotEmpty()) {
                _liveModels.value =
                    Result.Success<List<FakerUiModel>>().apply {
                        successBody = models
                    }
            } else {
                _liveModels.value = Result.Error(message = "There is no data to display")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}