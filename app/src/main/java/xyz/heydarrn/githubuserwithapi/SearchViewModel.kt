package xyz.heydarrn.githubuserwithapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel:ViewModel() {
    private var _listOfUser= MutableLiveData<ArrayList<ItemsItem>>()
    private val listOfUser: LiveData<ArrayList<ItemsItem>> = _listOfUser

    fun searchUser(username:String){
        val client=APIConfig.getApiService().searchUser(username)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful){
                    /*if successfully got response, insert the response body to arrayList "items"*/
                    _listOfUser.value= response.body()?.items
                }else{
                    Log.d("success search", "onResponse: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.d("fail search", "onFailure: ${t.stackTrace}")
            }

        })
    }

    fun setSearchUser():LiveData<ArrayList<ItemsItem>> = listOfUser

}