package org.kumnan.aos.apps.testpractice.ui.market

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.kumnan.aos.apps.domain.model.Item
import org.kumnan.aos.apps.testpractice.databinding.ItemMarketBinding

class ItemAdapter : ListAdapter<Item, ItemAdapter.ItemViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder = ItemViewHolder(
        ItemMarketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            title.text = item.title
            description.text = item.description
        }
    }

    class ItemViewHolder(val binding: ItemMarketBinding) : RecyclerView.ViewHolder(binding.root)
}

val COMPARATOR = object : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
        oldItem == newItem

}