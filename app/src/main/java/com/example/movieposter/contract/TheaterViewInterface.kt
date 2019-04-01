package com.example.movieposter.contract

import android.view.View
import android.view.View.INVISIBLE

interface TheaterViewInterface {

    fun setTheaterName(name: String)

    fun setTheaterAddress(address: String)

    fun setTimeOfSession(session: String)

    fun doInvisible()
}