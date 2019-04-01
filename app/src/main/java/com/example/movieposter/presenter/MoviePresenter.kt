package com.example.movieposter.presenter

import com.example.movieposter.contract.MoviePresenterInterface
import com.example.movieposter.contract.MovieViewInterface
import com.example.movieposter.model.Models

class MoviePresenter(var movies: Models.Movies) : MoviePresenterInterface {

    override fun onBindMovieAtPosition(position: Int, rowViewInterface: MovieViewInterface) {
        val movie = movies.result[position]
        rowViewInterface.setName(movie.name)
        if (movie.imdb != "false") {
            rowViewInterface.setRating("${movie.imdb} ★")
        } else{
            rowViewInterface.setRating("0,0 ★")
        }
        rowViewInterface.setCountry(movie.countries)
        rowViewInterface.setImage(movie.image)

    }

    override fun getMoviesCount() : Int {
        return movies.result.size
    }



}