package com.rivskyinc.imageflow.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rivskyinc.imageflow.Utils.Const.BASE_IMAGE_URL
import com.rivskyinc.imageflow.databinding.ItemsLayoutBinding
import com.rivskyinc.imageflow.domain.entities.Photo

class MyListAdapter(private val itemClickListener: (Photo, Int) -> Unit) : ListAdapter<Photo, MyListAdapter.PhotoViewHolder>(PhotoItemCallback()) {

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
        val url =
            BASE_IMAGE_URL + listOfPhotos.server +
                    "/" +
                    listOfPhotos.id +
                    "_" +
                    listOfPhotos.secret +
                    "_q.jpg"

        Glide.with(holder.itemView)
            .load(url)
            .into(holder.binding.mainImageView)

        holder.itemView.setOnClickListener {
            val photo = getItem(position)
            itemClickListener(photo, position)
        }

    }
}