package com.rivskyinc.imageflow.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rivskyinc.imageflow.databinding.ItemsLayoutBinding
import com.rivskyinc.imageflow.domain.entities.Photo

class MyListAdapter : ListAdapter<Photo, MyListAdapter.PhotoViewHolder>(PhotoItemCallback()) {

    var onClickListener: ((Photo) -> Unit)? = null

    class PhotoViewHolder(val binding: ItemsLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            ItemsLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

        val listOfPhotos = getItem(position)
        holder.binding.tvTitle.text = listOfPhotos.title
        Glide.with(holder.itemView).load(listOfPhotos.owner)
            .into(holder.binding.gifIv)


        holder.itemView.setOnClickListener {
            onClickListener?.invoke(listOfPhotos)
        }
    }
}