package com.rivskyinc.imageflow.domain

import com.rivskyinc.imageflow.domain.entities.Image

interface ImageRepository {

    suspend fun getAllImages() : Image

}