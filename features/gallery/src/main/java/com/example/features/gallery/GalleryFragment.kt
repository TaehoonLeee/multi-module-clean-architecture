package com.example.features.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import com.example.features.gallery.databinding.FragGalleryBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private val galleryViewModel: GalleryViewModel by viewModels()

    private lateinit var binding: FragGalleryBinding
    private lateinit var photoAdapter: UnsplashPhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragGalleryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoAdapter = UnsplashPhotoAdapter()

        binding.apply {
            rvPhoto.adapter = photoAdapter
        }

        lifecycleScope.launch {
            galleryViewModel.state.flowWithLifecycle(lifecycle).collect {
                photoAdapter.submitData(it.data)
            }
        }
    }
}