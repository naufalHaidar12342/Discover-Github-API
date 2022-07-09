package xyz.heydarrn.githubuserwithapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import xyz.heydarrn.githubuserwithapi.databinding.GithubUserBinding

class FollowerListAdapter:ListAdapter<FollowerResponse,FollowerListAdapter.FollowerViewHolder>(FollowerDiffCallback()) {
    class FollowerViewHolder(private val binding: GithubUserBinding):RecyclerView.ViewHolder(binding.root) {
        fun bindData(follower:FollowerResponse){
            binding.apply {
                Glide.with(itemView)
                    .load(follower.avatarUrl)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .into(userAvatar)
                usernameOfUser.text=itemView.resources.getString(R.string.username_template,follower.login)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val view=GithubUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FollowerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}