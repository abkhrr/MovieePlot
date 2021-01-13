package com.abkhrr.movieeplot.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.abkhrr.movieeplot.databinding.ToolbarHolderBinding
import com.abkhrr.movieeplot.databinding.ViewHolderEmptyViewBinding
import com.abkhrr.movieeplot.databinding.ViewHolderPopularMovieBinding
import com.abkhrr.movieeplot.databinding.ViewHolderUpcomingMovieBinding
import com.abkhrr.movieeplot.domain.dto.api.PopularMovieResult
import com.abkhrr.movieeplot.domain.dto.api.UpcomingMovieResult
import com.abkhrr.movieeplot.presentation.main.holder.EmptyViewHolder
import com.abkhrr.movieeplot.presentation.main.holder.PopularMovieHolder
import com.abkhrr.movieeplot.presentation.main.holder.ToolbarHolder
import com.abkhrr.movieeplot.presentation.main.holder.UpcomingMovieHolder
import com.abkhrr.movieeplot.presentation.main.listener.DashboardListener
import com.abkhrr.movieeplot.utils.android.recycler.type.TypeHolder
import com.abkhrr.movieeplot.utils.constant.AppConstant

class DashboardAdapter(
    val lifecycle: Lifecycle,
    val listener: DashboardListener
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val popularMovie: MutableList<PopularMovieResult>            = mutableListOf()
    val upcomingMovie: MutableList<UpcomingMovieResult>          = mutableListOf()
    private var menuPositionList : ArrayList<Pair<String,Int>>   = arrayListOf()
    var realMenuPositionList : ArrayList<Pair<String,Int>>       = arrayListOf()
        set(value) {
            field.apply {
                clear()
                addAll(value)
            }
            menuPositionList.clear()
            menuPositionList.addAll(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            TypeHolder.ITEM_TOOLBAR -> {
                ToolbarHolder(ToolbarHolderBinding.inflate(layoutInflater, parent,false))
            }
            TypeHolder.ITEM_POPULAR -> {
                PopularMovieHolder(ViewHolderPopularMovieBinding.inflate(layoutInflater, parent,false), listener, lifecycle)
            }
            TypeHolder.ITEM_UPCOMING  -> {
                UpcomingMovieHolder(ViewHolderUpcomingMovieBinding.inflate(layoutInflater, parent,false), listener)
            }
            else -> {
                EmptyViewHolder(ViewHolderEmptyViewBinding.inflate(layoutInflater, parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        return menuPositionList.size
    }

    override fun getItemViewType(position: Int): Int {
        with(TypeHolder){
            return getDashboardHolder(menuPositionList.getOrElse(position){ AppConstant.INITIAL_EMPTY_RECYCLER_VIEW to RecyclerView.NO_POSITION}.first)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            TypeHolder.ITEM_TOOLBAR -> {
                (holder as? ToolbarHolder)?.bind()
            }
            TypeHolder.ITEM_POPULAR -> {
                (holder as? PopularMovieHolder)?.bind(popularMovie)
            }
            TypeHolder.ITEM_UPCOMING -> {
                (holder as? UpcomingMovieHolder)?.bind(upcomingMovie)
            }
            else -> {
                (holder as? EmptyViewHolder)?.bind()
            }
        }
    }


}