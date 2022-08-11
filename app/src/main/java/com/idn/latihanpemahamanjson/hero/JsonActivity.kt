package com.idn.latihanpemahamanjson.hero

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.idn.latihanpemahamanjson.data.Hero
import com.idn.latihanpemahamanjson.databinding.ActivityJsonBinding
import java.io.IOException

class JsonActivity : AppCompatActivity() {

//    private val arrayHero = ArrayList<Hero>()

    private var _binding: ActivityJsonBinding? = null
    private val binding get() = _binding as ActivityJsonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityJsonBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            rvHero.layoutManager = LinearLayoutManager(this@JsonActivity)
            rvHero.adapter = HeroAdapter(this@JsonActivity)

            svHero.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        (rvHero.adapter as HeroAdapter).filter.filter(newText)
                    }
                    return true
                }

            })

        }

//        getDataGson()
//        Log.i("arrayHero", "onCreate : $arrayHero")
    }

//    private fun getDataGson() { //cara lain manggil gson cara lain
//
//        val rootJson = getDataFromGson()?.let { JSONObject(it) } //get Root Json
//        Log.i("JSON", "getPahlawanFromJson: $rootJson")
//
//    }

//    private fun AssetManager.readAssetFile(fileName: String): String {
//        return assets.open(fileName).bufferedReader().use { it.readText() }
//    }
//
//    private fun getDataFromGson(): String? { // cara lain manggil gson
//        val json: String
//        try {
//            val stream = applicationContext.assets.open("pahlawan_nasional.json")
//            val size = stream.available()
//            val buffer = ByteArray(size)
//            stream.read(buffer)
//            stream.close()
//            json = String(buffer, Charsets.UTF_8)
//        } catch (e: IOException) {
//            return null
//        }
//        return json
//    }
}
fun getDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioExcepTion: IOException) {
        ioExcepTion.printStackTrace()
        return null
    }
    return jsonString
}