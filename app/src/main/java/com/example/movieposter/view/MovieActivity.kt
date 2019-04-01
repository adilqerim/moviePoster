package com.example.movieposter.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.movieposter.presenter.MoviePresenter
import com.example.movieposter.R
import com.example.movieposter.adapter.Adapter
import com.example.movieposter.model.Models
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MovieActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleView_main.layoutManager = LinearLayoutManager(this)

        fetchJson()

    }

    private fun fetchJson() {
        val url = "https://kinoafisha.ua/ajax/kinoafisha_load"
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                val gson = GsonBuilder().create()

                val movies = gson.fromJson(body,Models.Movies::class.java)
                runOnUiThread{
                    recycleView_main.adapter = Adapter(MoviePresenter(movies))
                }
            }

        })
    }
}
