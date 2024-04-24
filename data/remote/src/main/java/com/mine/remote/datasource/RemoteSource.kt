package com.mine.remote.datasource

import com.mine.common.Resource
import com.mine.model.Photo

interface RemoteSource {
    suspend fun getMarsPhotos(): Resource<List<Photo>>
}