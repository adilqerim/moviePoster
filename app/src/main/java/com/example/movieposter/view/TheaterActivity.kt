package com.example.movieposter.view

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.view.View
import com.example.movieposter.R
import com.example.movieposter.adapter.TheaterAdapter
import com.example.movieposter.model.Models
import com.example.movieposter.presenter.TheaterPresenter
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movie_detail_activity.*
import okhttp3.*
import java.io.IOException

class TheaterActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleView_main.layoutManager = LinearLayoutManager(this)

        fetchTheaterJson()

        val mCountDownTimer = object : CountDownTimer(1000, 100) {

            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                progressBar.visibility = View.INVISIBLE
            }
        }
        mCountDownTimer.start()

    }

        fun fetchTheaterJson() {

        val url = "https://kinoafisha.ua/ajax/kinoafisha_load"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()


            client.newCall(request).enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()?.string()
                    val gson = GsonBuilder().create()
                    val currentPosition = intent.getIntExtra("position",0)
                    val movies = gson.fromJson(body, Models.Movies::class.java)
                        runOnUiThread {
                            recycleView_main.adapter = TheaterAdapter(TheaterPresenter(movies,currentPosition))

                        }
                }

            })
        }
}