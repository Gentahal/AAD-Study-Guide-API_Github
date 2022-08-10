package com.idn.latihanpemahamanjson.searchrepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.idn.latihanpemahamanjson.R
import com.idn.latihanpemahamanjson.data.RepoItem
import com.idn.latihanpemahamanjson.databinding.ActivitySearchRepoBinding
import com.idn.latihanpemahamanjson.searchuser.SearchUserViewModel
import com.idn.latihanpemahamanjson.searchuser.UserAdapter

class SearchRepoActivity : AppCompatActivity() {

    private var _binding : ActivitySearchRepoBinding? = null
    private val binding get() = _binding as ActivitySearchRepoBinding

    private var _viewModel : SearchRepoViewModel? = null
    private val viewModel get() = _viewModel as SearchRepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = ActivitySearchRepoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        _viewModel = ViewModelProvider(this)[SearchRepoViewModel::class.java]
        viewModel.getUserRepo().observe(this){
            Log.i("searchRepo", "onCreate: $it \n \n \n Repostories ----> ${it.item?.get(0)?.nama}")
            showDataRepo(it.item)
        }
        searchRepo()
    }

    private fun showDataRepo(data : List<RepoItem>?){
        binding.rvListRepo.apply {
            layoutManager = LinearLayoutManager(this@SearchRepoActivity)
            adapter = data?.let { RepoAdapter(it) }
        }
    }

    private fun searchRepo(){
        binding.svSearchRepo.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchRepos(it)
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