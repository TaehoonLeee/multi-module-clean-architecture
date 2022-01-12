package org.kumnan.aos.apps.testpractice.ui.market

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.kumnan.aos.apps.domain.model.Item
import org.kumnan.aos.apps.testpractice.R
import org.kumnan.aos.apps.testpractice.databinding.FragMarketBinding
import org.kumnan.aos.apps.testpractice.ui.base.BaseFragment
import org.kumnan.aos.apps.testpractice.ui.gallery.UnsplashPhotoAdapter

@AndroidEntryPoint
class MarketFragment : BaseFragment<FragMarketBinding>(R.layout.frag_market) {

    private val itemViewModel: MarketViewModel by viewModels()

    private lateinit var itemAdapter: ItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemAdapter = ItemAdapter()

        binding.rvItem.adapter = itemAdapter

        var cnt = 0
        binding.insertButton.setOnClickListener {
            itemViewModel.insertItem(Item("test${cnt++}", "test${cnt++}"))
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                itemViewModel.items.collect {
                    itemAdapter.submitList(it)
                }
            }
        }
    }
}