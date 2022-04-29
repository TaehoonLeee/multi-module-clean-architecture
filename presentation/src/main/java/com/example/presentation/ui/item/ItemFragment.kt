package com.example.presentation.ui.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.presentation.R
import com.example.presentation.databinding.FragMarketBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.kumnan.aos.apps.domain.model.Item
import com.example.presentation.ui.base.BaseFragment

@AndroidEntryPoint
class ItemFragment : BaseFragment<FragMarketBinding>(R.layout.frag_market) {

    private val itemViewModel: ItemViewModel by viewModels()

    private lateinit var itemAdapter: ItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemAdapter = ItemAdapter()

        binding.rvItem.adapter = itemAdapter

        var cnt = 0
        binding.insertButton.setOnClickListener {
            lifecycleScope.launch {
                itemViewModel.accept(ItemIntent.InsertItem(Item("test${cnt++}", "test${cnt++}")))
            }
        }

        lifecycleScope.launch {
            itemViewModel.state.flowWithLifecycle(lifecycle)
                .collect {
                    itemAdapter.submitList(it.items)
                }
        }
    }
}