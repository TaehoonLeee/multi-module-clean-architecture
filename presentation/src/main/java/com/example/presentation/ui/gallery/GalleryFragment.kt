package com.example.presentation.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.presentation.R
import com.example.presentation.databinding.FragGalleryBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.presentation.ui.base.BaseFragment

@AndroidEntryPoint
class GalleryFragment : BaseFragment<FragGalleryBinding>(R.layout.frag_gallery) {

    private val galleryViewModel: GalleryViewModel by viewModels()

    private lateinit var photoAdapter: UnsplashPhotoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoAdapter = UnsplashPhotoAdapter()

        binding.apply {
            rvPhoto.adapter = photoAdapter
        }

        galleryViewModel.searchResult.observe(viewLifecycleOwner) {
            photoAdapter.submitData(lifecycle, it)
        }
    }
}