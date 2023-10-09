package com.rivskyinc.imageflow.domain.entities

import com.rivskyinc.imageflow.domain.entities.PhotoX.PhotoX

data class Image(
    val extra: Extra,
    val photos: Photos,
    val stat: String
)