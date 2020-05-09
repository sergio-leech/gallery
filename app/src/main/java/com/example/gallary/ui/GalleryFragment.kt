package com.example.gallary.ui

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import com.example.gallary.databinding.FragmentGalleryBinding
import com.example.gallary.viewmodel.GalleryViewModel
import timber.log.Timber

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

        val edit = binding.searchText

        binding.searchBtn.setOnClickListener {
            if (edit.text.toString() != "") {
                Timber.d("EDIT TEXt= ${edit.text.toString()}")
                viewModelGallery.getSearchList(edit.text.toString())
                hideKeyboard()
            }
        }
        return binding.root
    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}


