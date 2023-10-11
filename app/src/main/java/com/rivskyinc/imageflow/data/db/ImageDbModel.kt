package com.rivskyinc.imageflow.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_items")
data class ImageDbModel (
val farm: Int,
@PrimaryKey(autoGenerate = false)
val id: String,
val isfamily: Int,
val isfriend: Int,
val ispublic: Int,
val owner: String,
val secret: String,
val server: String,
val title: String
)
