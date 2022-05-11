package com.example.presentation.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.presentation.R
import com.example.presentation.databinding.FragGalleryBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.presentation.ui.base.BaseFragment
import kotlinx.coroutines.launch

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

        lifecycleScope.launch {
            galleryViewModel.searchResult.flowWithLifecycle(lifecycle).collect {
                photoAdapter.submitData(it)
            }
        }
    }
}