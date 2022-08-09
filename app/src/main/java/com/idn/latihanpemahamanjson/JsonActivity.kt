package com.idn.latihanpemahamanjson

import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.idn.latihanpemahamanjson.data.Hero
import com.idn.latihanpemahamanjson.databinding.ActivityMainBinding
import org.json.JSONObject
import java.io.IOException

class JsonActivity : AppCompatActivity() {

    private val arrayHero = ArrayList<Hero>()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataGson()
        Log.i("arrayHero", "onCreate : $arrayHero")
    }

    private fun getDataGson() { //cara lain manggil gson cara lain

        val rootJson = getDataFromGson()?.let { JSONObject(it) } //get Root Json
        Log.i("JSON", "getPahlawanFromJson: $rootJson")

    }

    private fun AssetManager.readAssetFile(fileName: String): String {
        return assets.open(fileName).bufferedReader().use { it.readText() }
    }

    private fun getDataFromGson(): String? { // cara lain manggil gson
        val json: String
        try {
            val stream = applicationContext.assets.open("pahlawan_nasional.json")
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            return null
        }
        return json
    }
}