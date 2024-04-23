package com.mine.domain

import com.mine.model.Photo
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun getMarsPhotos(): Flow<List<Photo>>
}