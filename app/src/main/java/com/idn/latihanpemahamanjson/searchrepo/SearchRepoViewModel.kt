package com.idn.latihanpemahamanjson.searchrepo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.idn.latihanpemahamanjson.data.RepoResponse
import com.idn.latihanpemahamanjson.network.ApiConfig
import com.idn.latihanpemahamanjson.searchuser.SearchUserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepoViewModel : ViewModel() {

    private val listRepo = MutableLiveData<RepoResponse>()

    fun searchRepos(searchRepoString : String){
        ApiConfig.getApiService().searchRepo(searchRepoString).enqueue(object :
            Callback<RepoResponse> {
            override fun onResponse(call: Call<RepoResponse>, response: Response<RepoResponse>) {
                listRepo.value = response.body()
            }

            override fun onFailure(call: Call<RepoResponse>, t: Throwable) {
                Log.e("SearchRepo", "onFailure: $t", )
            }
        })
    }
    fun getUserRepo() : LiveData<RepoResponse> = listRepo
}