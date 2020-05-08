package com.example.gallary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.gallary.databinding.FragmentGalleryBinding
import com.example.gallary.viewmodel.GalleryViewModel

class GalleryFragment : Fragment() {
    private lateinit var viewModelGallery: GalleryViewModel
    private lateinit var binding: FragmentGalleryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGalleryBinding.inflate(inflater)
        binding.lifecycleOwner = this

        viewModelGallery = ViewModelProvider(this).get(GalleryViewModel::class.java)
        binding.galleryViewModel = viewModelGallery

        return binding.root
    }
}
