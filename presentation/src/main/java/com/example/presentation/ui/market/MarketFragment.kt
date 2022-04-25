package com.example.presentation.ui.market

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.presentation.R
import com.example.presentation.databinding.FragMarketBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.kumnan.aos.apps.domain.model.Item
import com.example.presentation.ui.base.BaseFragment

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