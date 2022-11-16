package com.example.features.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.Item
import com.example.features.item.databinding.FragMarketBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ItemFragment : Fragment(R.layout.frag_market) {

    private val itemViewModel: ItemViewModel by viewModels()

    private lateinit var binding: FragMarketBinding
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragMarketBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemAdapter = ItemAdapter()

        binding.rvItem.adapter = itemAdapter

        var cnt = 0
        binding.insertButton.setOnClickListener {
            lifecycleScope.launch {
                itemViewModel.process(
                    ItemAction.InsertItem(Item("test${cnt++}", "test${cnt++}"))
                )
            }
        }

        lifecycleScope.launch {
            itemViewModel.uiState.flowWithLifecycle(lifecycle).collect {
                itemAdapter.submitList(it.items)
            }
        }
    }
}