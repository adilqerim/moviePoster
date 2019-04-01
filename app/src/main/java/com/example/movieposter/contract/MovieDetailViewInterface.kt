package com.example.movieposter.contract

import com.example.movieposter.presenter.MovieDetailPresenter

interface MovieDetailViewInterface{

    fun setMovieDetail(name: String, image: String,country: String,actors: String,rating: String,vote_count: String)
}