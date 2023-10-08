package com.rivskyinc.imageflow.domain

import com.rivskyinc.imageflow.domain.entities.Image
import javax.inject.Inject

class GetImageUseCase @Inject constructor(private val imageRepository: ImageRepository) {

    suspend fun invoke() : Image? {
        return imageRepository.getAllImages()
    }
}