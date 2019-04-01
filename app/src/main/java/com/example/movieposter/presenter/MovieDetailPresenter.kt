package com.example.movieposter.presenter

import com.example.movieposter.contract.MovieDetailPresenterInterface
import com.example.movieposter.contract.MovieDetailViewInterface
import com.example.movieposter.model.Models
import kotlinx.android.synthetic.main.movie_detail_activity.*

class MovieDetailPresenter : MovieDetailPresenterInterface {

    override fun updateMovieDetailView(movies: Models.Movies, position: Int, view: MovieDetailViewInterface) {
        val movie = movies.result[position]
        view.setMovieDetail(movie.name,movie.image,movie.countries,movie.actors,movie.imdb,movie.count_vote)
    }

}