package xyz.heydarrn.githubuserwithapi

import androidx.recyclerview.widget.DiffUtil

class SearchDiffCallback:DiffUtil.ItemCallback<ItemsItem>() {
    override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
        return oldItem==newItem
    }
}