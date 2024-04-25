package com.mine.listscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mine.common.base.BaseViewModel
import com.mine.domain.MarsPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarsPhotosListViewModel @Inject constructor(private val marsPhotosUseCase: MarsPhotosUseCase) :
    BaseViewModel() {
    var uiState by mutableStateOf(MarsPhotosListUiState())
        private set

    init {
        getMarsPhotosList()
    }

    fun getMarsPhotosList() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            marsPhotosUseCase.getMarsPhotos().collect { result ->
                uiState = if (result.isNotEmpty()) {
                    uiState.copy(isLoading = false, marsPhotos = result)
                } else {
                    uiState.copy(isLoading = false, marsPhotos = result)
                }
            }
        }
    }

}
