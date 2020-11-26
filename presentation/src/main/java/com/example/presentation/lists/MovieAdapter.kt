package com.example.presentation.lists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.constants.Constants
import com.example.presentation.models.MoviePresentationModel
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    val navigateToDetails: PublishSubject<MoviePresentationModel> = PublishSubject.create()
    private var movies = emptyList<MoviePresentationModel>()
    lateinit var subscribeClick: Disposable


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        Glide.with(holder.itemView.context)
            .load(Constants.IMAGE_URL + movie.posterPath)
            .override(500, 500)
            .into(holder.itemView.findViewById(R.id.posterIV))
        holder.itemView.findViewById<TextView>(R.id.titleTV).text = movie.originalTitle
        subscribeClick = (holder.itemView.clicks().subscribe {
            navigateToDetails.onNext(movie)
        })
    }

    fun setData(list: List<MoviePresentationModel>) {
        movies = list
        notifyDataSetChanged()
    }
}

