package xyz.heydarrn.githubuserwithapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @Headers(GITHUB_TOKEN)
    @GET("search/users")
    fun searchUser(@Query("q") searchThisUser:String) : Call<SearchResponse>


    @GET("users/{username}")
    fun getSelectedUserDetail(@Path("username") usernameDetail:String) : Call<DetailResponse>


    @GET("users/{username}/followers")
    fun selectedUserFollower(@Path("username") usernameFollower:String): Call<ArrayList<FollowerResponse>>


    @GET("users/{username}/following")
    fun selectedUserFollowing(@Path("username") userFollowing: String) : Call<ArrayList<FollowingResponse>>

    companion object{
        private const val GITHUB_TOKEN=BuildConfig.PERSONAL_ACCESS_TOKEN
    }
}