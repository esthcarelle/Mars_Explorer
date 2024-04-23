package com.mine.repository

import com.mine.model.Photo
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun getMarsPhotos(): Flow<List<Photo>>
}