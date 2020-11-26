package com.example.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.constants.Constants
import com.example.presentation.databinding.FragmentDetailsScreenBinding
import com.example.presentation.mappers.toDomainModel
import com.example.presentation.vm.DetailsViewModel
import com.jakewharton.rxbinding3.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class DetailsScreen : Fragment(R.layout.fragment_details_screen) {
    private val viewModel: DetailsViewModel by viewModels()
    private val disposable = CompositeDisposable()
    private val args by navArgs<DetailsScreenArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentDetailsScreenBinding.bind(view).onViewBind()
    }

    private fun FragmentDetailsScreenBinding.onViewBind() {
        titleTV.text = args.movie.originalTitle
        overviewTV.text = args.movie.overview
        ratingBar.rating = args.movie.voteAverage.toFloat() / 2
        context?.let {
            Glide.with(it)
                .load(Constants.IMAGE_URL + args.movie.backdropPath)
                .into(posterIV)
        }

        disposable.add(favoriteButton.clicks().subscribe {
            if (!args.movie.isSelected) {
                favoriteButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                viewModel.addToFavorites(args.movie.toDomainModel())
                args.movie.isSelected = true
            } else {
                favoriteButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                viewModel.deleteFromFavorites(args.movie.toDomainModel())
                args.movie.isSelected = false
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()

    }
}