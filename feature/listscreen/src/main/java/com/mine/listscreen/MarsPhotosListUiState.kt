package com.mine.listscreen

import com.mine.model.Photo

data class MarsPhotosListUiState (
    var marsPhotos: List<Photo>? = null,
    val isLoading: Boolean = true
)