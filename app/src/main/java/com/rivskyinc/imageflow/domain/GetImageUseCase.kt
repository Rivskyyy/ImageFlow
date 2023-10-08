package com.rivskyinc.imageflow.domain

import com.rivskyinc.imageflow.domain.entities.Image

class GetImageUseCase(private val imageRepository: ImageRepository) {

    suspend fun getListOfImage() : Image {
        return imageRepository.getAllImages()
    }
}