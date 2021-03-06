package com.example.presentation.home

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.example.presentation.R
import com.example.presentation.extentions.hide
import com.example.presentation.extentions.show
import com.example.presentation.lists.MovieAdapter
import com.example.presentation.mappers.toPresentationModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class HomeScreen : Fragment() {
    private var page = 1
    private lateinit var adapter: MovieAdapter
    private val viewModel: HomeViewModel by viewModels()
    private val compositeDisposable = CompositeDisposable()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)
        adapter = MovieAdapter()
        val moviesRV = view.findViewById<RecyclerView>(R.id.movieRV)
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_circular)
        val searchView = view.findViewById<SearchView>(R.id.searchET)
        moviesRV.layoutManager = LinearLayoutManager(context)
        moviesRV.adapter = adapter
        compositeDisposable.add(viewModel.fetchMoviesFromServer(page)
            .doOnNext {
                progressBar.hide()
            }
            .subscribe({
                adapter.setData(it.map {
                    it.toPresentationModel()
                })

            }, {
                context?.let { context ->
                    MaterialDialog(context)
                        .show {
                            title(R.string.error_title)
                            message(R.string.error_message)
                            positiveButton(R.string.ok_button)
                        }
                }
            })
        )

        compositeDisposable.add(
            adapter.navigateToDetails.subscribe {
                val action = HomeScreenDirections.actionHomeScreenToDetailsScreen(it)
                view.findNavController().navigate(action)
            }
        )

        moviesRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val items = (moviesRV.layoutManager as LinearLayoutManager).childCount
                    val pastVisibleItems =
                        (moviesRV.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                    val total = adapter.itemCount
                    if (items + pastVisibleItems >= total) {
                        progressBar.show()
                        compositeDisposable.add(
                            viewModel.fetchMoviesFromServer(page++)
                                .doOnNext {
                                    progressBar.hide()
                                }.subscribe({
                                    adapter.setData(it.map {
                                        it.toPresentationModel()
                                    })
                                }, {
                                    d("issuedb", it.message.toString())
                                })
                        )
                    }
                }
            }
        })
        compositeDisposable.add(
            Observable.create(ObservableOnSubscribe<String> {
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(newText: String?): Boolean {
                        it.onNext(newText!!)
                        return true
                    }

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }
                })
            }).map {
                it.toLowerCase().trim()
            }
                .debounce(250, TimeUnit.MILLISECONDS)
                .distinct()
                .filter { text -> text.isNotBlank() }
                .subscribe {
                    viewModel.searchMovie(it).subscribe({
                        adapter.setData(it.map {
                            it.toPresentationModel()
                        })
                    },
                        {
                            d("error", it.message.toString())
                        })
                })





        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}