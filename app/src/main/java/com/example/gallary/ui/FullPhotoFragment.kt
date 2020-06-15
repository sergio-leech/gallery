package com.example.gallary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.gallary.databinding.FragmentFullPhotoBinding
import com.example.gallary.viewmodel.FullPhotoViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FullPhotoFragment : Fragment() {
    private val fullPhotoViewModel by viewModels<FullPhotoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentFullPhotoBinding.inflate(inflater, container, false)
        val id = arguments?.let { arguments ->
            FullPhotoFragmentArgs.fromBundle(arguments).id
        }
        Timber.d("ID : $id")

        binding.fullPhotoViewModel = fullPhotoViewModel
        binding.lifecycleOwner = this
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
        fullPhotoViewModel.getPhoto(id.toString())
        return binding.root
    }
}
