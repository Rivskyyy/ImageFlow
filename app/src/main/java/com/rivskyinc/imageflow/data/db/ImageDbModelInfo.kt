package com.rivskyinc.imageflow.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rivskyinc.imageflow.domain.entities.PhotoX.Comments
import com.rivskyinc.imageflow.domain.entities.PhotoX.Dates
import com.rivskyinc.imageflow.domain.entities.PhotoX.Description
import com.rivskyinc.imageflow.domain.entities.PhotoX.Editability
import com.rivskyinc.imageflow.domain.entities.PhotoX.Notes
import com.rivskyinc.imageflow.domain.entities.PhotoX.Owner
import com.rivskyinc.imageflow.domain.entities.PhotoX.People
import com.rivskyinc.imageflow.domain.entities.PhotoX.Publiceditability
import com.rivskyinc.imageflow.domain.entities.PhotoX.Tags
import com.rivskyinc.imageflow.domain.entities.PhotoX.Title
import com.rivskyinc.imageflow.domain.entities.PhotoX.Urls
import com.rivskyinc.imageflow.domain.entities.PhotoX.Usage
import com.rivskyinc.imageflow.domain.entities.PhotoX.Visibility

@Entity(tableName = "image_info")
class ImageDbModelInfo (
    val comments: Comments,
    val dates: Dates,
    val dateuploaded: String,
    val description: Description,
    val editability: Editability,
    val farm: Int,
    @PrimaryKey(autoGenerate = false)
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

