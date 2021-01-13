package com.abkhrr.movieeplot.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abkhrr.movieeplot.BuildConfig
import com.abkhrr.movieeplot.databinding.ViewItemPopularMovieBinding
import com.abkhrr.movieeplot.domain.dto.api.PopularMovieResult
import com.abkhrr.movieeplot.presentation.main.listener.DashboardListener
import com.abkhrr.movieeplot.presentation.main.listener.popular_movie.PopularMovieItemClick
import com.abkhrr.movieeplot.utils.android.image.UtilImage


class PopularMovieAdapter(private val listener: DashboardListener): RecyclerView.Adapter<PopularMovieAdapter.ViewHolder>() {

    val items: MutableList<PopularMovieResult> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ViewItemPopularMovieBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: ViewItemPopularMovieBinding): RecyclerView.ViewHolder(
        binding.root
    ){
        fun bind(position: Int){

            val popularMovie     = items[position]
            binding.popularMovie = popularMovie
            val imageUrl         = checkImageUrl(popularMovie)
            binding.listener     = PopularMovieItemClick { listener.onPopularMovieClick(popularMovie) }

            binding.viewPopularMovieImage.apply {
                UtilImage.loadImagePoster(
                    binding.viewPopularMovieImage,
                    imageUrl,
                    binding.root.context
                )
            }

            binding.executePendingBindings()
        }

        private fun checkImageUrl(movie: PopularMovieResult): String {
            return if (movie.posterPath.isNotEmpty()) {
                BuildConfig.IMAGE_BASE_URL + movie.posterPath
            } else {
                BuildConfig.IMAGE_BASE_URL + movie.backdropPath
            }
        }
    }
}