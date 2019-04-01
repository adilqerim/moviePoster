package com.example.movieposter.contract

import com.example.movieposter.model.Models

interface MovieDetailPresenterInterface {

    fun updateMovieDetailView(movies: Models.Movies, position: Int, view: MovieDetailViewInterface)
}