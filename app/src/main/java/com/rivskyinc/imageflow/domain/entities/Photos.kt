package com.rivskyinc.imageflow.domain.entities

import com.rivskyinc.imageflow.domain.entities.PhotoX.PhotoX

data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val photo: List<Photo>,
    val total: Int
)