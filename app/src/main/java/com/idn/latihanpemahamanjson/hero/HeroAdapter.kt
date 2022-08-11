package com.idn.latihanpemahamanjson.hero

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.idn.latihanpemahamanjson.data.DaftarPahlawanItem
import com.idn.latihanpemahamanjson.data.HeroResponse
import com.idn.latihanpemahamanjson.databinding.RowItemHeroBinding

class HeroAdapter(var context: Context) : RecyclerView.Adapter<HeroAdapter.MyViewHolder>() {

    private val heroList : List<DaftarPahlawanItem> = Gson().fromJson(
        getDataFromAsset(context, "pahlawan_nasional.json").toString(),
        HeroResponse::class.java
    ).daftarPahlawan as List<DaftarPahlawanItem>

    class MyViewHolder(val binding: RowItemHeroBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        RowItemHeroBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val hero = heroList[position]
        holder.binding.apply {
            tvNamaHero.text = hero.nama
            tvLengkapHero.text = hero.nama2
            Glide.with(imageHero).load(hero.img).into(imageHero)
        }
    }

    override fun getItemCount(): Int = heroList.size
}