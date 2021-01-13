package com.abkhrr.movieeplot.presentation.main.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abkhrr.movieeplot.BuildConfig
import com.abkhrr.movieeplot.R
import com.abkhrr.movieeplot.data.source.AppDataSource
import com.abkhrr.movieeplot.domain.dto.api.*
import com.abkhrr.movieeplot.domain.dto.api.result.Result
import com.abkhrr.movieeplot.presentation.base.BaseViewModel
import com.abkhrr.movieeplot.utils.android.log.MyLog
import com.abkhrr.movieeplot.utils.android.recycler.type.TypeHolder
import com.abkhrr.movieeplot.utils.api.ApiUtils
import com.abkhrr.movieeplot.utils.event.Event
import com.abkhrr.movieeplot.utils.helper.SessionHelper
import kotlinx.coroutines.launch

class SharedViewModel(private val app: Application, private val appDataSource: AppDataSource): BaseViewModel() {

    private val popularMovieResponseLiveData: MutableLiveData<List<PopularMovieResult>> = MutableLiveData()
    private val upcomingMovieLiveData: MutableLiveData<List<UpcomingMovieResult>>       = MutableLiveData()
    private val movieLiveData                                                           = MutableLiveData<Event<MovieResponse>>()
    private val movieReviewLiveData: MutableLiveData<List<MovieReviewResult>>           = MutableLiveData()

    fun fetchPopularMovie(){
        viewModelScope.launch {
            isLoading.value = true
            when (val result = appDataSource.getPopularMovies()) {
                is Result.Success<PopularMovieResponse> -> {
                    result.data.results.let { mapPopularMovie(it) }
                    isLoading.value = false
                }
                is Result.Error -> {
                    val fourHundredError     = "Something Went Wrong..."
                    showSnack.value          = ApiUtils().getErrorMessage(result.statusCode, result.typeError, fourHundredError)
                    MyLog.e(TAG, "e/$TAG: ${result.message}")
                    isLoading.value = false
                }
            }
        }
    }

    fun fetchUpcomingMovie(){
        viewModelScope.launch {
            isLoading.value = true
            when (val result = appDataSource.getUpcomingMovies()) {
                is Result.Success<UpcomingMovieResponse> -> {
                    result.data.results.let { mapUpcomingMovie(it) }
                    isLoading.value = false
                }
                is Result.Error -> {
                    val fourHundredError     = "Something Went Wrong..."
                    showSnack.value          = ApiUtils().getErrorMessage(result.statusCode, result.typeError, fourHundredError)
                    MyLog.e(TAG, "e/$TAG: ${result.message}")
                    isLoading.value = false
                }
            }
        }
    }

    fun fetchMovieDetail(movieId: Int){
        viewModelScope.launch {
            isLoading.value = true
            when (val result = appDataSource.getDetailMovie(movieId)) {
                is Result.Success<MovieResponse> -> {
                    if (isNullOrEmpty(result.data.tagline) || result.data.tagline === ""){
                        result.data.tagline = "This Movie Does Not Have Tag-line"
                    }
                    movieLiveData.value = Event(result.data)
                    isLoading.value = false
                }
                is Result.Error -> {
                    val fourHundredError           = "Something Went Wrong..."
                    showSnack.value                = ApiUtils().getErrorMessage(result.statusCode, result.typeError, fourHundredError)
                    MyLog.e(TAG, "e/$TAG: ${result.message}")
                    isLoading.value = false
                }
            }
        }
    }

    fun fetchMovieReview(movieId: Int){
        isLoading.value = true
        viewModelScope.launch {
            when (val result = appDataSource.getMovieReviews(movieId)) {
                is Result.Success<MovieReviewResponse> -> {
                    if (result.data.totalResults == 0){
                        showSnack.value = "This Movie Does Not Have Review Yet..."
                        isLoading.value = false
                    }
                    result.data.results.let { mapMovieReview(it) }
                    isLoading.value = false
                }
                is Result.Error -> {
                    val fourHundredError     = "Something Went Wrong..."
                    showSnack.value          = ApiUtils().getErrorMessage(result.statusCode, result.typeError, fourHundredError)
                    MyLog.e(TAG, "e/$TAG: ${result.message}")
                    isLoading.value = false
                }
            }
        }
    }

    init {
        fetchPopularMovie()
        fetchUpcomingMovie()
    }

    val popularMovieData: LiveData<List<PopularMovieResult>>
        get() = popularMovieResponseLiveData

    val upcomingMovieData: LiveData<List<UpcomingMovieResult>>
        get() = upcomingMovieLiveData

    val movieData : LiveData<Event<MovieResponse>>
        get() = movieLiveData

    val movieReviewData: LiveData<List<MovieReviewResult>>
        get() = movieReviewLiveData

    private fun mapPopularMovie(popularMovie: List<PopularMovieResult>?){
        popularMovieResponseLiveData.value = popularMovie?.map {
            PopularMovieResult(
                it.adult,
                it.backdropPath,
                it.genreIds,
                it.id,
                it.originalLanguage,
                it.originalTitle,
                it.overview,
                it.popularity,
                it.posterPath,
                it.releaseDate,
                it.originalTitle,
                it.video,
                it.voteAverage,
                it.voteCount
            )
        }
    }

    private fun mapUpcomingMovie(upcomingMovie: List<UpcomingMovieResult>?){
        upcomingMovieLiveData.value = upcomingMovie?.map {
            UpcomingMovieResult(
                it.adult,
                it.backdropPath,
                it.genreIds,
                it.id,
                it.originalLanguage,
                it.originalTitle,
                it.overview,
                it.popularity,
                it.posterPath,
                it.releaseDate,
                it.originalTitle,
                it.video,
                it.voteAverage,
                it.voteCount
            )
        }
    }

    private fun mapMovieReview(movieReviews: List<MovieReviewResult>?){
        movieReviewLiveData.value = movieReviews?.map {
            MovieReviewResult(
                it.author,
                it.authorDetails,
                it.content,
                it.createdAt,
                it.id,
                it.updatedAt,
                it.url
            )
        }
    }

    fun getAppVersions(): String {
        return String.format(app.getString(R.string.app_version), BuildConfig.VERSION_NAME)
    }

    fun setSession(username: String, session: Boolean, context: Context){
        val sessionHelper = SessionHelper(context)
        sessionHelper.setUsername(username, session)
    }

    fun getRecyclerPositionList(): ArrayList<Pair<String,Int>> {
        with(TypeHolder){
            return arrayListOf(
                HOLDER_NAME_TOOLBAR    to 0,
                HOLDER_NAME_POPULAR    to 1,
                HOLDER_NAME_UPCOMING   to 2,
                HOLDER_NAME_OTHER      to 3
            )
        }
    }

    private fun isNullOrEmpty(str: String?): Boolean {
        if (str != null && str.trim().isNotEmpty())
            return false
        return true
    }

    companion object{
        private val TAG = SharedViewModel::class.java.simpleName
    }

}