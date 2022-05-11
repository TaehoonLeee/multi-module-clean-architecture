package com.example.presentation.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.UnsplashPhoto
import com.example.presentation.databinding.ItemUnsplashPhotoBinding

class UnsplashPhotoAdapter : PagingDataAdapter<UnsplashPhoto, UnsplashPhotoAdapter.UnsplashPhotoViewHolder>(UnsplashPhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnsplashPhotoViewHolder = UnsplashPhotoViewHolder(
        ItemUnsplashPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: UnsplashPhotoViewHolder, position: Int) {
        val currentItem = getItem(position)?: return

        holder.binding.textViewUserName.text = currentItem.user.name
        Glide.with(holder.binding.imageView)
            .load(currentItem.urls.regular)
            .centerCrop()
            .into(holder.binding.imageView)
    }

    class UnsplashPhotoViewHolder(val binding: ItemUnsplashPhotoBinding) : RecyclerView.ViewHolder(binding.root)
}

class UnsplashPhotoDiffCallback: DiffUtil.ItemCallback<UnsplashPhoto>() {
    override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean
        = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean
        = oldItem == newItem
}