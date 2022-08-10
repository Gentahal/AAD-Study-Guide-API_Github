package com.idn.latihanpemahamanjson.listuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idn.latihanpemahamanjson.UsersItem
import com.idn.latihanpemahamanjson.databinding.RowItemUserBinding

class ListUserAdapter(private val listUser : List<UsersItem>) : RecyclerView.Adapter<ListUserAdapter.UserViewHolder>() {

    class UserViewHolder(val binding: RowItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        RowItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val list = listUser[position]
        holder.binding.apply {
            tvNamaUser.text = list.login
            tvNamaUserLengkap.text = list.htmlUrl
            Glide.with(imageUser).load(list.avatarUrl).into(imageUser)
        }
    }

    override fun getItemCount(): Int = listUser.size
}