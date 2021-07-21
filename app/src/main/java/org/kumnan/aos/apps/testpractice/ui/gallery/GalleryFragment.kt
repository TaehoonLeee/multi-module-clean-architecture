package org.kumnan.aos.apps.testpractice.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.kumnan.aos.apps.domain.entity.status.Result
import org.kumnan.aos.apps.testpractice.R
import org.kumnan.aos.apps.testpractice.databinding.FragGalleryBinding
import org.kumnan.aos.apps.testpractice.ui.base.BaseFragment

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

        galleryViewModel.searchPageResult.observe(viewLifecycleOwner) {

        }

        galleryViewModel.searchResult.observe(viewLifecycleOwner) {
            photoAdapter.submitData(lifecycle, it)
        }
    }
}