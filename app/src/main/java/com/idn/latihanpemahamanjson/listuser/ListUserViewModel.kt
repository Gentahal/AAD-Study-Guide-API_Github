package com.idn.latihanpemahamanjson.listuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idn.latihanpemahamanjson.UsersItem
import com.idn.latihanpemahamanjson.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListUserViewModel : ViewModel() {

    //listUser ini di gunakan Menampung data setelah di ambil dari API
    val listUser = MutableLiveData<List<UsersItem>>()
    
    fun getListUser(){
        ApiConfig.getApiService().getListUsers().enqueue(object : Callback<List<UsersItem>> {
            override fun onResponse(
                call: Call<List<UsersItem>>,
                //data sudah berada di parameterr response ketika fungsi getListUser() di pakai
                response: Response<List<UsersItem>>
            ) {
                //mengisi value list user yang masih kosong dengan data response

                listUser.value = response.body()
//                listUser.postValue(response.body())
            }

            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                Log.e("TAG", "onFailure: $t ")
            }
        })
    }

    //fungsi yang bertugas menyediakan data untuk di tampilkan di UI Controler
    fun getResulListUser(): LiveData<List<UsersItem>> = listUser
}