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
import io.reactivex.disposables.CompositeDisposable

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val dissolve = CompositeDisposable()
    private var movies = emptyList<MoviePresentationModel>()

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
    }

    fun setData(list: List<MoviePresentationModel>) {
        movies = list
        notifyDataSetChanged()
    }

    override fun onViewDetachedFromWindow(holder: MovieViewHolder) {
        super.onViewDetachedFromWindow(holder)
        dissolve.addAll()
    }
}

