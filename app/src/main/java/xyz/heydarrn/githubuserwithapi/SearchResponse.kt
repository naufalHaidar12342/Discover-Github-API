package xyz.heydarrn.githubuserwithapi

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @field:SerializedName("total_count")
    val totalCount: Int,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    @field:SerializedName("items")
    val items: ArrayList<ItemsItem>
)

data class ItemsItem(
    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("id")
    val id:Int
)
