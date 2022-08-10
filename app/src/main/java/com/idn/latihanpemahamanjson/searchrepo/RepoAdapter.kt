package com.idn.latihanpemahamanjson.searchrepo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idn.latihanpemahamanjson.data.RepoItem
import com.idn.latihanpemahamanjson.data.RepoResponse
import com.idn.latihanpemahamanjson.databinding.RowItemRepoBinding

class RepoAdapter(private val listRepo: List<RepoItem>) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    class RepoViewHolder (val binding: RowItemRepoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RepoViewHolder(
        RowItemRepoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val data = listRepo [position]
        holder.binding.apply {
            tvNamaRepo.text = data.nama
            tvFullnameRepo.text = data.fullName
            tvDesc.text = data.description
        }
    }

    override fun getItemCount(): Int = listRepo.size
}