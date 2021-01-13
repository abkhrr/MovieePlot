package com.abkhrr.movieeplot.presentation.main.listener.popular_movie

class PopularMovieItemClick(private val onMovieClick: () -> Unit) {
    fun onMovieClick() = onMovieClick.invoke()
}