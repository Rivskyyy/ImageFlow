package com.rivskyinc.imageflow.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImageDao {

    @Query("SELECT * FROM image_items")
    suspend fun getImageList() : LiveData<List<ImageDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImageList(imageList : ImageDbModel)

    @Query("SELECT * FROM image_info WHERE id=:imageId LIMIT 1 ")
    suspend fun getImageInfo(imageId : String) : ImageDbModel

}