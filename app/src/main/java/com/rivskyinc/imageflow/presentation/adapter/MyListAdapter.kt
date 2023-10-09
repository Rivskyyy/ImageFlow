package com.rivskyinc.imageflow.presentation.adapter

import android.util.Log
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
        val url =
            "https://live.staticflickr.com/" + listOfPhotos.server +
                    "/" +
                    listOfPhotos.id +
                    "_" +
                    listOfPhotos.secret +
                    ".jpg"

        Glide.with(holder.itemView)
            .load(url)
            .into(holder.binding.mainImageView)

        Log.d(
            "Adapter",
            url)

        holder.itemView.setOnClickListener {
            onClickListener?.invoke(listOfPhotos)
        }
    }
}