package com.rivskyinc.imageflow.domain.UseCases

import com.rivskyinc.imageflow.domain.ImageRepository
import com.rivskyinc.imageflow.domain.entities.PhotoX.Result
import javax.inject.Inject

class GetImageDetailUseCase @Inject constructor(private val imageRepository: ImageRepository) {

    suspend fun invoke(id : String ) : Result? {
        return imageRepository.getDetail(id)
    }


}