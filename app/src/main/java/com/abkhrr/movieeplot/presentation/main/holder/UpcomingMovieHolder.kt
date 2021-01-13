package com.abkhrr.movieeplot.presentation.main.holder

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abkhrr.movieeplot.databinding.ViewHolderUpcomingMovieBinding
import com.abkhrr.movieeplot.domain.dto.api.UpcomingMovieResult
import com.abkhrr.movieeplot.presentation.main.adapter.UpcomingMovieAdapter
import com.abkhrr.movieeplot.presentation.main.listener.DashboardListener

class UpcomingMovieHolder(
    private val binding: ViewHolderUpcomingMovieBinding,
    listener: DashboardListener
    ) : RecyclerView.ViewHolder(binding.root) {

    private var adapterUpcomingMovie = UpcomingMovieAdapter(listener)

    fun bind(items: List<UpcomingMovieResult>){
        val gridLayoutManager     = GridLayoutManager(binding.root.context, 2)
        binding.viewCollectionRecyclerview.apply {
            layoutManager = gridLayoutManager
            adapter       = adapterUpcomingMovie
        }
        binding.viewCollectionRecyclerview.visibility = View.VISIBLE
        adapterUpcomingMovie.items.clear()
        adapterUpcomingMovie.items.addAll(items)
        adapterUpcomingMovie.notifyDataSetChanged()
        binding.executePendingBindings()
    }
}