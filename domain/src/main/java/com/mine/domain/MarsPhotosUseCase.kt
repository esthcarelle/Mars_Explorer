package com.mine.domain

import com.mine.model.Photo
import com.mine.repository.DataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarsPhotosUseCase @Inject constructor(
    private val dataRepository: DataSource
) : UseCase {
    override fun getMarsPhotos(): Flow<List<Photo>> {
        return dataRepository.getMarsPhotos()
    }
}