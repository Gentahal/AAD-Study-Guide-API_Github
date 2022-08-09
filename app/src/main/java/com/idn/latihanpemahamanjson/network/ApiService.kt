package com.idn.latihanpemahamanjson.network

import com.idn.latihanpemahamanjson.UsersItem
import com.idn.latihanpemahamanjson.UsersResponse
import com.idn.latihanpemahamanjson.data.RepoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getListUsers(): Call<List<UsersItem>>

    @GET("search/users")
    fun searchUsers(
        @Query("q") username: String
    ) : Call<UsersResponse>

    @GET("search/repositories")
    fun searchRepo(
        @Query("q") repositories: String
    ) :Call<RepoResponse>
}