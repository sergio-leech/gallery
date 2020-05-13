package com.example.gallary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.gallary.R
import com.example.gallary.databinding.FragmentFullPhotoBinding
import com.example.gallary.viewmodel.FullPhotoViewModel
import com.example.gallary.viewmodel.FullPhotoViewModelFactory
import timber.log.Timber


class FullPhotoFragment : Fragment() {
    private lateinit var fullPhotoViewModelFactory: FullPhotoViewModelFactory
    private lateinit var fullPhotoViewModel: FullPhotoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFullPhotoBinding.inflate(inflater,container,false)
        val application = requireNotNull(this.activity).application
        val id = arguments?.let {arguments ->
            FullPhotoFragmentArgs.fromBundle(arguments).id }
        Timber.d("ID : $id")

        fullPhotoViewModelFactory = FullPhotoViewModelFactory(application,id.toString())
        fullPhotoViewModel = ViewModelProvider(this,fullPhotoViewModelFactory).get(FullPhotoViewModel::class.java)
        binding.fullPhotoViewModel=fullPhotoViewModel
        binding.lifecycleOwner = this
        /*binding.backBtn.setNavigationOnClickListener{

        }*/

        return binding.root
    }

}
