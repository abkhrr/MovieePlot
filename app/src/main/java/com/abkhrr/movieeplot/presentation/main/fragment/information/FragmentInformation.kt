package com.abkhrr.movieeplot.presentation.main.fragment.information

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.abkhrr.movieeplot.BR
import com.abkhrr.movieeplot.BuildConfig
import com.abkhrr.movieeplot.R
import com.abkhrr.movieeplot.databinding.FragmentInformationBinding
import com.abkhrr.movieeplot.domain.dto.api.MovieResponse
import com.abkhrr.movieeplot.factory.ViewModelProviderFactory
import com.abkhrr.movieeplot.presentation.base.BaseFragment
import com.abkhrr.movieeplot.presentation.main.viewmodel.SharedViewModel
import com.abkhrr.movieeplot.utils.android.image.UtilImage
import com.abkhrr.movieeplot.utils.event.EventObserver
import javax.inject.Inject

class FragmentInformation : BaseFragment<FragmentInformationBinding, SharedViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_information

    override val viewModel: SharedViewModel
        get() = ViewModelProvider(this, factory).get(SharedViewModel::class.java)

    private var movieId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (parentFragment?.arguments != null){
            movieId = parentFragment?.arguments?.getInt("movieId")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieId?.let {
            viewModel.fetchMovieDetail(it)
        }
        checkIsContentLoad()
    }

    private fun checkIsContentLoad(){
        viewModel.movieData.observe(viewLifecycleOwner, EventObserver{ movie ->
            val imageUrl     = checkImageUrl(movie)
            val movieId      = movie.imdbId
            val voteAverage  = movie.voteAverage.toString()
            val overview     = movie.overview

            getViewDataBinding().movieTitleInfo.text    = movie.title
            getViewDataBinding().movieTagLineInfo.text  = movie.tagline
            getViewDataBinding().movieImdbIdInfo.text   = halfBoldString(movieId, R.string.imdbId, 8)
            getViewDataBinding().movieImdbVoteInfo.text = halfBoldString(voteAverage, R.string.imdbVote, 6)
            getViewDataBinding().movieOverviewInfo.text = halfBoldString(overview, R.string.movieOverview, 12)
            UtilImage.loadImagePoster(getViewDataBinding().movieImageInfo, imageUrl, requireContext())
        })
    }

    private fun halfBoldString(text: String, resId: Int, spannableEnd: Int): SpannableString {
        val stringImdbId = resources.getString(resId)
        val imdbId       = String.format(stringImdbId, text)

        val string       = SpannableString(imdbId)
        string.setSpan(StyleSpan(Typeface.BOLD), 0, spannableEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return string
    }

    private fun checkImageUrl(movie: MovieResponse): String {
        return if (movie.posterPath.isNotEmpty()) {
            BuildConfig.IMAGE_BASE_URL + movie.posterPath
        } else {
            BuildConfig.IMAGE_BASE_URL + movie.backdropPath
        }
    }

}