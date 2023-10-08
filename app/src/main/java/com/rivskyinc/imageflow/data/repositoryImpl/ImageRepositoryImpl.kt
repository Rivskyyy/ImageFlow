package com.rivskyinc.imageflow.data.repositoryImpl

import com.rivskyinc.imageflow.data.ImageApi
import com.rivskyinc.imageflow.domain.ImageRepository
import com.rivskyinc.imageflow.domain.entities.Image
import retrofit2.Response

class  ImageRepositoryImpl(private val imageApi : ImageApi) : ImageRepository {
    override suspend fun getAllImages(): Image? {
        try {
            val response: Response<Image?> = imageApi.imageResponse()

            if (response.isSuccessful) {
                return response.body() ?: throw RuntimeException("Response body is null")
            } else {
//                throw RuntimeException("HTTP Error: ${response.code()}")
                return null
            }
        } catch (e: Exception) {
//            throw RuntimeException("Request Error: ${e.message}")
            return null
        }

    }
}