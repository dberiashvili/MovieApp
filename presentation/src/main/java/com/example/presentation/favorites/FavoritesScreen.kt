package com.example.presentation.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.presentation.R
import com.example.presentation.databinding.FragmentFavoritesScreenBinding
import com.example.presentation.lists.MovieAdapter
import com.example.presentation.mappers.toPresentationModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class FavoritesScreen : Fragment(R.layout.fragment_favorites_screen) {
    private val adapter = MovieAdapter()
    private val compositeDisposable = CompositeDisposable()
    private val viewModel: FavoritesViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentFavoritesScreenBinding.bind(view).onViewBind()
    }

    private fun FragmentFavoritesScreenBinding.onViewBind() {
        favoritesRV.layoutManager = GridLayoutManager(context, 2)
        favoritesRV.adapter = adapter
        compositeDisposable.add(viewModel.getFavoriteMovies().subscribe {
            it.map {
                it.toPresentationModel()
            }
        })


    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}