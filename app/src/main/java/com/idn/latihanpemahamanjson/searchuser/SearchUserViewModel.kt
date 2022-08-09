package com.idn.latihanpemahamanjson.searchuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idn.latihanpemahamanjson.UsersResponse
import com.idn.latihanpemahamanjson.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchUserViewModel: ViewModel() {

    private val listUser = MutableLiveData<UsersResponse>() //livedata adalah ketika data dari APInya Di update dia ngikut terUp

    fun searchUser(searchViewString: String) {
        ApiConfig.getApiService().searchUsers(searchViewString).enqueue(object : Callback<UsersResponse> {
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
//                listUser.value = response.body()
                if (response.isSuccessful) listUser.postValue(response.body())
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                Log.e("SearchUser", "onFailure: $t", )
            }
        })
    }

    fun getSearchUser() : LiveData<UsersResponse> = listUser
}