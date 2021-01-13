package com.abkhrr.movieeplot.data.remote

import com.abkhrr.movieeplot.domain.dto.api.MovieResponse
import com.abkhrr.movieeplot.domain.dto.api.MovieReviewResponse
import com.abkhrr.movieeplot.domain.dto.api.PopularMovieResponse
import com.abkhrr.movieeplot.domain.dto.api.UpcomingMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(EndPoint.POPULAR_MOVIE)
    suspend fun getPopularMovies(@Query("api_key") api_key: String): PopularMovieResponse

    @GET(EndPoint.UPCOMING_MOVIE)
    suspend fun getUpcomingMovies(@Query("api_key") api_key: String): UpcomingMovieResponse

    @GET(EndPoint.MOVIE_DETAILS)
    suspend fun getDetailMovie(@Path("movie_id") id: Int, @Query("api_key") api_key: String): MovieResponse

    @GET(EndPoint.MOVIE_REVIEW)
    suspend fun getMovieReviews(@Path("movie_id") id: Int, @Query("api_key") api_key: String ): MovieReviewResponse

}