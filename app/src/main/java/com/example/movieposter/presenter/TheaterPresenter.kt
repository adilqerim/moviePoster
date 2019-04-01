package com.example.movieposter.presenter

import android.text.Html
import com.example.movieposter.contract.TheaterPresenterInterface
import com.example.movieposter.contract.TheaterViewInterface
import com.example.movieposter.model.Models

class TheaterPresenter(var movies : Models.Movies,val currentPosition: Int) : TheaterPresenterInterface{



    override fun onBindTheaterAtPosition(position: Int, rowViewInterface: TheaterViewInterface) {

        val movie = movies.result[currentPosition]
        val theater = movie.sessions[position]
        val html = theater.sessions
        if (theater.k_name != null && theater.k_addr != null) {
                rowViewInterface.setTheaterName("Кинотеатр: " + theater.k_name)
                rowViewInterface.setTheaterAddress("Адрес: " + theater.k_addr)
                rowViewInterface.setTimeOfSession(Html.fromHtml(html).toString())
        } else {
            rowViewInterface.doInvisible()
        }
    }

    override fun getTheatersCount(): Int {
        return movies.result[currentPosition].sessions.size
    }

}