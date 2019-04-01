package com.example.movieposter.contract

interface MoviePresenterInterface {

    fun onBindMovieAtPosition(position: Int, rowViewInterface: MovieViewInterface)
    fun getMoviesCount() : Int

}