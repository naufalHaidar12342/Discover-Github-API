package xyz.heydarrn.githubuserwithapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import xyz.heydarrn.githubuserwithapi.databinding.GithubUserBinding

class FollowingListAdapter:ListAdapter<FollowingResponse,FollowingListAdapter.FollowingViewHolder>(FollowingDiffCallback()) {
    class FollowingViewHolder(private val binding: GithubUserBinding):RecyclerView.ViewHolder(binding.root) {
        fun bindData(following:FollowingResponse){
            binding.apply {
                Glide.with(itemView)
                    .load(following.avatarUrl)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .into(userAvatar)
                usernameOfUser.text=itemView.resources.getString(R.string.username_template,following.login)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val view=GithubUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FollowingViewHolder(view)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}