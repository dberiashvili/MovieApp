package com.example.presentation.screens

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.lists.MovieAdapter
import com.example.presentation.mappers.toPresentationModel
import com.example.presentation.vm.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

@AndroidEntryPoint
class HomeScreen : Fragment() {
    private lateinit var adapter: MovieAdapter
    private lateinit var displayMovies:Disposable
    private val viewModel: MovieViewModel by viewModels()
    private val dissolve = CompositeDisposable()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)
        adapter = MovieAdapter()
        val moviesRV = view.findViewById<RecyclerView>(R.id.movieRV)
        moviesRV.layoutManager = LinearLayoutManager(context)
        moviesRV.adapter = adapter
        displayMovies = viewModel.fetchMoviesFromServer(1)
            .subscribe (
                {
            adapter.setData(it.map {
                it.toPresentationModel()
            })
        }, {error->
                   d("errorMessage", error.message.toString())
                })
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        dissolve.addAll()
    }


}