package com.idn.latihanpemahamanjson.searchrepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.idn.latihanpemahamanjson.R

class SearchRepoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_repo)

        val viewModel = ViewModelProvider(this)[SearchRepoViewModel::class.java]
        viewModel.searchRepos("Adam")
        viewModel.getUserRepo().observe(this){
            Log.i("searchRepo", "onCreate: $it \n \n \n Repostories ----> ${it.item?.get(0)?.nama}")
        }
    }
}