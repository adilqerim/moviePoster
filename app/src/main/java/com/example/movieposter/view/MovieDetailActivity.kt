package com.example.movieposter.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import com.example.movieposter.R
import com.example.movieposter.contract.MovieDetailViewInterface
import com.example.movieposter.model.Models
import com.example.movieposter.presenter.MovieDetailPresenter
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_detail_activity.*
import okhttp3.*
import java.io.IOException
import android.R.attr.start
import android.os.CountDownTimer
import android.widget.ProgressBar
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MovieDetailActivity : AppCompatActivity(),MovieDetailViewInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.movieposter.R.layout.movie_detail_activity)

        fetchDetailJson()


        val mCountDownTimer = object : CountDownTimer(1000, 100) {

            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                progressbar1.visibility = View.INVISIBLE
            }
        }
        mCountDownTimer.start()
    }

    private fun fetchDetailJson() {
        val url = "https://kinoafisha.ua/ajax/kinoafisha_load"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()


        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                val gson = GsonBuilder().create()
                val currentPosition = intent.getIntExtra("position", 0)
                val movies = gson.fromJson(body, Models.Movies::class.java)
                val presenter = MovieDetailPresenter()
                presenter.updateMovieDetailView(movies, currentPosition,this@MovieDetailActivity)
            }

        })
    }

    override fun setMovieDetail(
        name: String,
        image: String,
        country: String,
        actors: String,
        rating: String,
        vote_count: String
    ) {
        runOnUiThread {
            textView_name.text = name
            if (rating != "false") {
                rating_textView.text = ("$rating ★")
            } else{
                rating_textView.text = ("0,0 ★")
            }


            vote_count_textView.text = vote_count
            countries_textView.text = country
            actors_textView.text = ("В ролях: " + Html.fromHtml(actors).toString())
            var imageUrl = "https://kinoafisha.ua$image"
            imageUrl = imageUrl.replace("sm_", "")
            Picasso.get().load(imageUrl).into(movie_imageView)
        }
    }


}