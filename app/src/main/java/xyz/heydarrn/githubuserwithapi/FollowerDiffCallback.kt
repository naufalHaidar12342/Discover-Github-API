package xyz.heydarrn.githubuserwithapi

import androidx.recyclerview.widget.DiffUtil

class FollowerDiffCallback:DiffUtil.ItemCallback<FollowerResponse>() {
    override fun areItemsTheSame(oldItem: FollowerResponse, newItem: FollowerResponse): Boolean {
        return oldItem.login==newItem.login
    }

    override fun areContentsTheSame(oldItem: FollowerResponse, newItem: FollowerResponse): Boolean {
        return oldItem==newItem
    }
}