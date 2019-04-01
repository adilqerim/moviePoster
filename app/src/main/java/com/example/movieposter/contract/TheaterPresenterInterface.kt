package com.example.movieposter.contract

interface TheaterPresenterInterface {

    fun onBindTheaterAtPosition(position: Int, rowViewInterface : TheaterViewInterface)

    fun getTheatersCount() : Int

}