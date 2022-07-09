package xyz.heydarrn.githubuserwithapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel:ViewModel() {
    private var _listOfUserDetail= MutableLiveData<DetailResponse>()
    private val listOfUserDetail: LiveData<DetailResponse> = _listOfUserDetail

    fun getUserDetail(username:String){
        APIConfig.getApiService().getSelectedUserDetail(username).enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if (response.isSuccessful){
                    _listOfUserDetail.value=response.body()
                }else{
                    Log.d("user detail success", "onResponse: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.d("user detail failed", "onResponse: ${t.printStackTrace()}")
            }

        })
    }

    fun setUserDetail():LiveData<DetailResponse> = listOfUserDetail
}