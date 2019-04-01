package com.example.movieposter.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieposter.R
import com.example.movieposter.contract.MovieViewInterface
import com.example.movieposter.presenter.MovieDetailPresenter
import com.example.movieposter.presenter.MoviePresenter
import com.example.movieposter.view.MovieDetailActivity
import com.example.movieposter.view.TheaterActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list_item.view.*

class Adapter(val moviePresenter: MoviePresenter) : RecyclerView.Adapter<MovieViewInterfaceHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieViewInterfaceHolder {
       return MovieViewInterfaceHolder(
           LayoutInflater.from(p0.context).inflate(
               R.layout.movie_list_item,
               p0,
               false
           )
       )
    }

    override fun getItemCount(): Int {
        return moviePresenter.getMoviesCount()
    }

    override fun onBindViewHolder(p0: MovieViewInterfaceHolder, p1: Int) {
        moviePresenter.onBindMovieAtPosition(p1,p0)
    }


}
class MovieViewInterfaceHolder(itemView : View) : RecyclerView.ViewHolder(itemView), MovieViewInterface {
    override fun setName(name: String) {
        itemView.tv_name.text = name
    }

    override fun setRating(rating: String) {
        itemView.tv_rating.text = rating
    }

    override fun setCountry(country: String) {
        itemView.tv_country.text = country
    }

    override fun setImage(image: String) {
        var imageUrl = "https://kinoafisha.ua$image"
            imageUrl = imageUrl.replace("sm_","")
        Picasso.get().load(imageUrl).into(itemView.imageView)
    }

    init {
        itemView.about_button.setOnClickListener {
            val intent = Intent(itemView.context,MovieDetailActivity::class.java)
            intent.putExtra("position",adapterPosition)
            itemView.context.startActivity(intent)
        }

        itemView.sessions_button.setOnClickListener {
            val intent = Intent(itemView.context,TheaterActivity::class.java)
            intent.putExtra("position",adapterPosition)
            itemView.context.startActivity(intent)
        }
    }

}

