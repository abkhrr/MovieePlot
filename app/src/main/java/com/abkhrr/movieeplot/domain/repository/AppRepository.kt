package com.abkhrr.movieeplot.domain.repository

import com.abkhrr.movieeplot.data.remote.ApiService
import com.abkhrr.movieeplot.data.source.AppDataSource
import com.abkhrr.movieeplot.di.annotation.ApiInfo
import com.abkhrr.movieeplot.domain.dto.api.MovieResponse
import com.abkhrr.movieeplot.domain.dto.api.MovieReviewResponse
import com.abkhrr.movieeplot.domain.dto.api.PopularMovieResponse
import com.abkhrr.movieeplot.domain.dto.api.UpcomingMovieResponse
import javax.inject.Inject
import com.abkhrr.movieeplot.domain.dto.api.result.Result
import com.abkhrr.movieeplot.utils.api.ErrorCodes
import com.abkhrr.movieeplot.utils.api.TypeError
import retrofit2.HttpException
import java.net.SocketTimeoutException

class AppRepository @Inject constructor(private val apiService: ApiService, @param:ApiInfo private val apiKey: String)
    : AppDataSource {

    override suspend fun getPopularMovies(): Result<PopularMovieResponse> {
        return try {
            val popularMovie = apiService.getPopularMovies(apiKey)
            return Result.Success(popularMovie)
        } catch (e: Exception){
            getExceptionType(e)
        }
    }

    override suspend fun getUpcomingMovies(): Result<UpcomingMovieResponse> {
        return try {
            val upcomingMovie = apiService.getUpcomingMovies(apiKey)
            return Result.Success(upcomingMovie)
        } catch (e: Exception){
            getExceptionType(e)
        }
    }

    override suspend fun getDetailMovie(id: Int): Result<MovieResponse> {
        return try {
            val movieDetail = apiService.getDetailMovie(id, apiKey)
            return Result.Success(movieDetail)
        } catch (e: Exception){
            getExceptionType(e)
        }
    }

    override suspend fun getMovieReviews(id: Int): Result<MovieReviewResponse> {
        return try {
            val movieReview = apiService.getMovieReviews(id, apiKey)
            return Result.Success(movieReview)
        } catch (e: Exception){
            getExceptionType(e)
        }
    }

    private fun getExceptionType(e: Exception) : Result.Error {
        return when (e) {
            is HttpException -> Result.Error(e.message(), e.code(), TypeError.ERROR_HTTP)
            is SocketTimeoutException -> Result.Error(e.localizedMessage, ErrorCodes.SocketTimeOut.code, TypeError.ERROR_SOCKET)
            else                      -> Result.Error(e.localizedMessage, Int.MAX_VALUE, TypeError.NETWORK_ERROR)
        }
    }

}