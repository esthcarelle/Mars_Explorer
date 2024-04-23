package com.mine.repository

import com.mine.common.Resource
import com.mine.model.Photo
import com.mine.remote.datasource.RemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class DataRepository @Inject
constructor(
    private val remoteDataSource: RemoteSource
) : DataSource {

    override fun getMarsPhotos(): Flow<List<Photo>> {
        return flow {
            when (remoteDataSource.getMarsPhotos()) {
                is Resource.Success -> {
                    remoteDataSource.getMarsPhotos().data?.let { emit(it) }
                }
                is Resource.DataError -> {
                    emitAll(flowOf(emptyList()))
                }
                else -> {
                    emitAll(flowOf(emptyList()))
                }
            }
        }
    }
}