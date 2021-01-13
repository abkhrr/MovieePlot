package com.abkhrr.movieeplot.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abkhrr.movieeplot.BuildConfig
import com.abkhrr.movieeplot.R
import com.abkhrr.movieeplot.databinding.ViewItemUpcomingMovieBinding
import com.abkhrr.movieeplot.domain.dto.api.UpcomingMovieResult
import com.abkhrr.movieeplot.presentation.main.listener.DashboardListener
import com.abkhrr.movieeplot.presentation.main.listener.upcoming_movie.UpcomingMovieItemClick
import com.abkhrr.movieeplot.utils.android.image.UtilImage

class UpcomingMovieAdapter(private val listener: DashboardListener): RecyclerView.Adapter<UpcomingMovieAdapter.UpcomingHolder>() {

    val items: MutableList<UpcomingMovieResult> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UpcomingHolder(ViewItemUpcomingMovieBinding.inflate(inflater, parent,false))
    }

    override fun onBindViewHolder(holder: UpcomingHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class UpcomingHolder(private val binding: ViewItemUpcomingMovieBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(position: Int){
            val upcomingMovie     = items[position]
            binding.upcomingMovie = upcomingMovie
            val imageUrl          = checkImageUrl(upcomingMovie)
            binding.listener      = UpcomingMovieItemClick { listener.onUpcomingMovieClick(upcomingMovie) }

            binding.upcomingImage.apply {
                UtilImage.loadImageWithoutPlaceholder(this, imageUrl , binding.root.context, R.dimen.PADDING_OCTAR)
            }
            binding.executePendingBindings()
        }

        private fun checkImageUrl(movie: UpcomingMovieResult): String {
            return if (movie.posterPath.isNotEmpty()) {
                BuildConfig.IMAGE_BASE_URL + movie.posterPath
            } else {
                BuildConfig.IMAGE_BASE_URL + movie.backdropPath
            }
        }
    }
}