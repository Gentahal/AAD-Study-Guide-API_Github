package com.idn.latihanpemahamanjson.hero

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.idn.latihanpemahamanjson.data.DaftarPahlawanItem
import com.idn.latihanpemahamanjson.data.HeroResponse
import com.idn.latihanpemahamanjson.databinding.RowItemHeroBinding
import java.util.*
import java.util.logging.LogRecord
import kotlin.collections.ArrayList

class HeroAdapter(var context: Context) : RecyclerView.Adapter<HeroAdapter.MyViewHolder>(), Filterable {

    private val heroList : MutableList<DaftarPahlawanItem> = (Gson().fromJson(
        getDataFromAsset(context, "pahlawan_nasional.json").toString(),
        HeroResponse::class.java
    ).daftarPahlawan as List<DaftarPahlawanItem>).toMutableList()

    private val heroFilter : Filter = object : Filter(){
        override fun performFiltering(constrain: CharSequence?): FilterResults {
            val filterList: MutableList<DaftarPahlawanItem> = ArrayList()
            if ( constrain == null || constrain.length == 0 ) {
                filterList.addAll(heroList)
            }else{
                val filterPattern = constrain.toString().lowercase(Locale.getDefault())
                for (item: DaftarPahlawanItem in heroList) {
                    if (item.nama?.lowercase(Locale.ROOT)?.contains(filterPattern) == true) {
                        filterList.add(item)
                    }
                }
            }
            val filterResult = FilterResults()
            filterResult.values = filterList
            return filterResult
        }

        override fun publishResults(contain: CharSequence?, result: FilterResults?) {
            heroList.clear()
            heroList.addAll(result?.values as List<DaftarPahlawanItem>)
            notifyDataSetChanged()

        }

    }

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

    override fun getFilter() : Filter = heroFilter
}