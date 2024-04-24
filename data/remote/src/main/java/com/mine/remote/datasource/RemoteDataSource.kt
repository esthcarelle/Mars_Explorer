package com.mine.remote.datasource

import android.util.Log
import com.mine.common.Resource
import com.mine.model.Photo
import com.mine.remote.network.MarsPhotosApi
import javax.inject.Inject

class RemoteDataSource @Inject
constructor(private val api: MarsPhotosApi) : RemoteSource {

    override suspend fun getMarsPhotos(): Resource<List<Photo>> {
        try {
            val res = api.fetchPhotos()

            when (res.isSuccessful) {
                true -> {
                    res.body()?.let { body ->
                        if (body.photos.isNotEmpty()) {
                            return Resource.Success(data = body.photos)
                        } else return Resource.DataError(errorCode = 101)
                    } ?: return Resource.DataError(errorCode = res.code())
                }
                false -> {
                    return Resource.DataError(errorCode = res.code())
                }
            }
        } catch (e: Exception) {
            Log.e("NETWORK_API_ERROR", "Cannot get mars images ${e.message}")
            return Resource.DataError(errorCode = e.hashCode())
        }
    }
}