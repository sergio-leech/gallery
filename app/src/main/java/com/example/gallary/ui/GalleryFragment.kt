package com.example.gallary.ui

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import com.example.gallary.databinding.FragmentGalleryBinding
import com.example.gallary.viewmodel.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class GalleryFragment : Fragment() {
    private val viewModelGallery by viewModels<GalleryViewModel>()
    private lateinit var binding: FragmentGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGalleryBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.galleryViewModel = viewModelGallery

        val edit = binding.searchText

        binding.searchBtn.setOnClickListener {
            if (edit.text.toString() != "") {
                Timber.d("EDIT TEXt= ${edit.text}")
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


