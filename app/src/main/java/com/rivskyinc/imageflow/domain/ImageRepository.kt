package com.rivskyinc.imageflow.domain

import com.rivskyinc.imageflow.domain.entities.Image
import com.rivskyinc.imageflow.domain.entities.PhotoX.Result
import com.rivskyinc.imageflow.domain.entities.Photos

interface ImageRepository {

    suspend fun getAllImages() : Image?

    suspend fun getDetail(id : String) : Result?
}