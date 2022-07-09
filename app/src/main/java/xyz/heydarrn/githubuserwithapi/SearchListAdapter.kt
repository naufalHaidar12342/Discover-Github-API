package xyz.heydarrn.githubuserwithapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import xyz.heydarrn.githubuserwithapi.databinding.GithubUserBinding

class SearchListAdapter:ListAdapter<ItemsItem,SearchListAdapter.SearchViewHolder>(SearchDiffCallback()) {
    var whenUserChosen:OnUserClicked?=null
    fun openDetailWithUsername(thisUser:OnUserClicked){
        this.whenUserChosen=thisUser
    }
    inner class SearchViewHolder(private val bindingUser: GithubUserBinding):RecyclerView.ViewHolder(bindingUser.root) {
        fun bindData(user:ItemsItem){
            bindingUser.apply {
                Glide.with(itemView)
                    .load(user.avatarUrl)
                    .circleCrop()
                    .into(userAvatar)
                usernameOfUser.text=itemView.context.getString(R.string.username_template,user.login)

                root.setOnClickListener {
                    whenUserChosen?.chooseUser(user.login)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view=GithubUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    interface OnUserClicked{
        fun chooseUser(userChosen:String)
    }
}