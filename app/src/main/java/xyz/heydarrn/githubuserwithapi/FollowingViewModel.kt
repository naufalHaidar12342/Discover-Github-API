package xyz.heydarrn.githubuserwithapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel:ViewModel() {
    private var _listUserFollowing= MutableLiveData<ArrayList<FollowingResponse>>()
    private val listUserFollowing: LiveData<ArrayList<FollowingResponse>> = _listUserFollowing

    fun getFollower(usernameFollowing:String){
        APIConfig.getApiService().selectedUserFollowing(usernameFollowing).enqueue(object :
            Callback<ArrayList<FollowingResponse>> {
            override fun onResponse(
                call: Call<ArrayList<FollowingResponse>>,
                response: Response<ArrayList<FollowingResponse>>
            ) {
                if (response.isSuccessful && !response.body().isNullOrEmpty()){
                    _listUserFollowing.value=response.body()
                }else{
                    Log.d("following success", "onResponse: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<FollowingResponse>>, t: Throwable) {
                Log.d("following failed", "onResponse: ${t.message}")
            }

        })
    }

    fun setFollowing(): LiveData<ArrayList<FollowingResponse>> = listUserFollowing
}