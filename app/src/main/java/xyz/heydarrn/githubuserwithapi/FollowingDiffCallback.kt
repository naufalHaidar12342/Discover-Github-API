package xyz.heydarrn.githubuserwithapi

import androidx.recyclerview.widget.DiffUtil

class FollowingDiffCallback:DiffUtil.ItemCallback<FollowingResponse>() {
    override fun areItemsTheSame(oldItem: FollowingResponse, newItem: FollowingResponse): Boolean {
        return oldItem.login==newItem.login
    }

    override fun areContentsTheSame(
        oldItem: FollowingResponse,
        newItem: FollowingResponse
    ): Boolean {
        return oldItem==newItem
    }
}