package com.rivskyinc.imageflow.domain.entities.PhotoX

data class PhotoX(
    val comments: Comments,
    val dates: Dates,
    val dateuploaded: String,
    val description: Description,
    val editability: Editability,
    val farm: Int,
    val id: String,
    val isfavorite: Int,
    val license: String,
    val media: String,
    val notes: Notes,
    val originalformat: String,
    val originalsecret: String,
    val owner: Owner,
    val people: People,
    val publiceditability: Publiceditability,
    val rotation: Int,
    val safety_level: String,
    val secret: String,
    val server: String,
    val tags: Tags,
    val title: Title,
    val urls: Urls,
    val usage: Usage,
    val views: String,
    val visibility: Visibility
)