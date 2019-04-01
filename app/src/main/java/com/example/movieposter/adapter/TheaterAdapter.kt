package com.example.movieposter.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieposter.R
import com.example.movieposter.contract.TheaterViewInterface
import com.example.movieposter.presenter.TheaterPresenter
import kotlinx.android.synthetic.main.theater_list_item.view.*



class TheaterAdapter(val presenter: TheaterPresenter) : RecyclerView.Adapter<TheaterViewInterfaceHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TheaterViewInterfaceHolder {
        return TheaterViewInterfaceHolder(LayoutInflater.from(p0.context).inflate(R.layout.theater_list_item,p0,false))
    }

    override fun getItemCount(): Int {
        return presenter.getTheatersCount()
    }

    override fun onBindViewHolder(p0: TheaterViewInterfaceHolder, p1: Int) {
        presenter.onBindTheaterAtPosition(p1,p0)
    }

}

class TheaterViewInterfaceHolder(itemView: View) : RecyclerView.ViewHolder(itemView), TheaterViewInterface {

    override fun setTheaterName(name: String) {
        itemView.tv_theater_name.text = name
    }

    override fun setTheaterAddress(address: String) {
        itemView.tv_theater_address.text = address
    }

    override fun setTimeOfSession(session: String) {
        itemView.tv_theater_session.append(session)
    }

    override fun doInvisible() {
        val param = itemView.layoutParams as RecyclerView.LayoutParams
        itemView.visibility = View.GONE
        param.height = 0
        param.width = 0
        itemView.layoutParams = param
    }

}
