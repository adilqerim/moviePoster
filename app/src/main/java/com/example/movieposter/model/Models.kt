package com.example.movieposter.model


class Models{

    class Movies(val result: List<Movie>)

    class Movie(val name: String,val image: String,val count_vote: String, val imdb: String,val actors: String,
                val countries:String,val premier_ua: String, val sessions: List<MovieTheater>)


    class MovieTheater(val k_name: String, val k_addr: String,val sessions: String)
}