package com.idn.latihanpemahamanjson.listuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.idn.latihanpemahamanjson.R
import com.idn.latihanpemahamanjson.UsersItem
import com.idn.latihanpemahamanjson.databinding.ActivityListUserBinding
import com.idn.latihanpemahamanjson.searchuser.UserAdapter

class ListUserActivity : AppCompatActivity() {

    private var _binding : ActivityListUserBinding? = null
    private val binding get() = _binding as ActivityListUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viedModel = ViewModelProvider(this) [ListUserViewModel::class.java]
        viedModel.getListUser()
        viedModel.getResulListUser().observe(this) {
            Log.i("ListUser", "onCreate: $it")
            showDataUser(it)
        }
    }

    private fun showDataUser(data: List<UsersItem>){
        binding.rvUserList.apply {
            layoutManager = LinearLayoutManager(this@ListUserActivity)
            adapter = data?.let { ListUserAdapter(it) }
        }
    }
}