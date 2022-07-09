package xyz.heydarrn.githubuserwithapi

import com.google.gson.annotations.SerializedName

data class FollowingResponse(
    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("html_url")
    val htmlUrl: String,

    @field:SerializedName("login")
    val login: String
)
