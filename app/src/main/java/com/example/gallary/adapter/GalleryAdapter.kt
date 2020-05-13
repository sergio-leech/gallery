package com.example.gallary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gallary.databinding.GalleryItemBinding
import com.example.gallary.model.Photo
import com.example.gallary.ui.GalleryFragmentDirections
import timber.log.Timber

class GalleryAdapter : ListAdapter<Photo, GalleryAdapter.GalleryViewHolder>(DiffCallback) {

    class GalleryViewHolder(private var binding: GalleryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener { view ->
                binding.galleryItem?.let { photo ->
                    navigateToFullPhoto(photo, view)
                }
            }
        }

        private fun navigateToFullPhoto(photo: Photo, view: View) {
            Timber.d("ID: ${photo.id}")
            val direction =
                GalleryFragmentDirections.actionGallaryFragmentToFullPhotoFragment(photo.id)
            view.findNavController().navigate(direction)
        }

        fun bind(galleryItem: Photo) {
            binding.galleryItem = galleryItem
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(GalleryItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}