package com.abkhrr.movieeplot.presentation.main.listener

import com.abkhrr.movieeplot.domain.dto.api.PopularMovieResult
import com.abkhrr.movieeplot.domain.dto.api.UpcomingMovieResult

interface DashboardListener {
    fun onPopularMovieClick(movie: PopularMovieResult)
    fun onUpcomingMovieClick(movie: UpcomingMovieResult)
}