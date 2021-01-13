package com.abkhrr.movieeplot.presentation.main.listener.upcoming_movie

class UpcomingMovieItemClick(private val onMovieClick: () -> Unit) {
    fun onMovieClick() = onMovieClick.invoke()
}