package xyz.heydarrn.githubuserwithapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel:ViewModel() {
    private var _listUserFollower= MutableLiveData<ArrayList<FollowerResponse>>()
    private val listUserFollower: LiveData<ArrayList<FollowerResponse>> = _listUserFollower

    fun getFollower(usernameFollower:String){
        APIConfig.getApiService().selectedUserFollower(usernameFollower).enqueue(object : Callback<ArrayList<FollowerResponse>> {
            override fun onResponse(
                call: Call<ArrayList<FollowerResponse>>,
                response: Response<ArrayList<FollowerResponse>>
            ) {
                if (response.isSuccessful && !response.body().isNullOrEmpty()){
                    _listUserFollower.postValue(response.body())
                }else{
                    Log.d("follower success", "onResponse: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<FollowerResponse>>, t: Throwable) {
                Log.d("follower failed", "onResponse: ${t.printStackTrace()}")
            }

        })
    }

    fun setFollower():LiveData<ArrayList<FollowerResponse>> = listUserFollower
}