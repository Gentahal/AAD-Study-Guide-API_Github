package com.idn.latihanpemahamanjson.searchuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.idn.latihanpemahamanjson.R
import com.idn.latihanpemahamanjson.UsersItem
import com.idn.latihanpemahamanjson.UsersResponse
import com.idn.latihanpemahamanjson.databinding.ActivitySearchUserBinding

class SearchUserActivity : AppCompatActivity() {

    private var _binding : ActivitySearchUserBinding? = null
    private val binding get() = _binding as ActivitySearchUserBinding

    private var _viewModel : SearchUserViewModel? = null
    private val viewModel get() = _viewModel as SearchUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchViewString = "Genta"

        //inisialisasi ViewModel
         _viewModel = ViewModelProvider(this)[SearchUserViewModel::class.java]
        viewModel.getSearchUser().observe(this){
            Log.i("TAG", "onCreate: $it \n \n \n Nama Usernya ----> ${it.items?.get(0)?.login}")
            showDataUser(it.items)
        }

        searchUsers()

    }

    private fun showDataUser(data : List<UsersItem>?) {
        Log.d("user", "showDataUser: $data")
        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(this@SearchUserActivity)
            adapter = data?.let { UserAdapter(it) }
        }
    }

    private fun searchUsers(){
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchUser(it)
                }
                try {
                    val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

                    inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken,0)
                } catch (e: Throwable) {
                    Log.e("searchUser", "onQueryTextSubmit: $e", )
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }
}