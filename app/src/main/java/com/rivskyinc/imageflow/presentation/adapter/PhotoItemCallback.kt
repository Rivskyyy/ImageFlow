package com.rivskyinc.imageflow.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rivskyinc.imageflow.domain.entities.Photo

class PhotoItemCallback : DiffUtil.ItemCallback<Photo>() {

    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {

        return oldItem == newItem

    }
}