package com.idn.latihanpemahamanjson.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.idn.latihanpemahamanjson.R
import com.idn.latihanpemahamanjson.listuser.ListUserViewModel

class ListUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)

        val viedModel = ViewModelProvider(this) [ListUserViewModel::class.java]
        viedModel.getListUser()
        viedModel.getResulListUser().observe(this) {
            Log.i("ListUser", "onCreate: $it")
        }
    }
}