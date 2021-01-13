package com.abkhrr.movieeplot.data.source

import com.abkhrr.movieeplot.domain.dto.api.MovieResponse
import com.abkhrr.movieeplot.domain.dto.api.MovieReviewResponse
import com.abkhrr.movieeplot.domain.dto.api.PopularMovieResponse
import com.abkhrr.movieeplot.domain.dto.api.UpcomingMovieResponse
import com.abkhrr.movieeplot.domain.dto.api.result.Result

interface AppDataSource {
    suspend fun getPopularMovies()           : Result<PopularMovieResponse>
    suspend fun getUpcomingMovies()          : Result<UpcomingMovieResponse>
    suspend fun getDetailMovie(id: Int)      : Result<MovieResponse>
    suspend fun getMovieReviews(id: Int)     : Result<MovieReviewResponse>
}