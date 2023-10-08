package com.rivskyinc.imageflow.data.repositoryImpl

import com.rivskyinc.imageflow.data.ImageApi
import com.rivskyinc.imageflow.domain.ImageRepository
import com.rivskyinc.imageflow.domain.entities.Image
import retrofit2.Response
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(private val imageApi: ImageApi) : ImageRepository {
    override suspend fun getAllImages(): Image? {
        return try {
            val response: Response<Image?> = imageApi.imageResponse()

            if (response.isSuccessful) {
                response.body() ?: throw RuntimeException("Response body is null")
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }

    }
}