package com.abkhrr.movieeplot.presentation.main.holder

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abkhrr.movieeplot.R
import com.abkhrr.movieeplot.databinding.ViewHolderPopularMovieBinding
import com.abkhrr.movieeplot.domain.dto.api.PopularMovieResult
import com.abkhrr.movieeplot.presentation.base.BaseViewHolder
import com.abkhrr.movieeplot.presentation.main.adapter.PopularMovieAdapter
import com.abkhrr.movieeplot.presentation.main.listener.DashboardListener
import com.abkhrr.movieeplot.utils.android.pager.CirclePagerIndicator
import com.abkhrr.movieeplot.utils.android.pager.OneByOneSnapHelper
import com.abkhrr.movieeplot.utils.android.recycler.DecorationSpacingLinear

class PopularMovieHolder(
        private val binding: ViewHolderPopularMovieBinding,
        private val listener: DashboardListener,
        lifecycle: Lifecycle,
        ): BaseViewHolder(binding.root) {

    private lateinit var adapterPopularMovie: PopularMovieAdapter
    private val decoration         = CirclePagerIndicator()
    private val itemDecoration     = DecorationSpacingLinear(binding.root.context, R.dimen.PADDING_OCTAR, true)
    private var handler: Handler?  = null
    private var isScrolling        = false

    init {
        initializeCollection()
        lifecycle.addObserver(this)
    }

    override fun onLifeCycleStart() {
        handler = Handler(Looper.getMainLooper())
        resetAutoScroll()
    }

    override fun onLifeCycleStop() {
        handler?.removeCallbacksAndMessages(null)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initializeCollection(){
        adapterPopularMovie = PopularMovieAdapter(listener)
        binding.popularMovieShimmer.visibility = View.VISIBLE
        binding.recyclerViewCollectionPopularMovie.apply {
            clipToPadding = false
            adapter       = adapterPopularMovie
            addItemDecoration(decoration)
            addItemDecoration(itemDecoration)
            visibility    = View.INVISIBLE
        }
        val snapHelper = OneByOneSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewCollectionPopularMovie)
        binding.executePendingBindings()
    }

    fun bind(items: List<PopularMovieResult>){

        if (items.isEmpty()) {
            binding.popularMovieShimmer.visibility = View.VISIBLE
            return
        }

        if(items.size == 1) {
            adapterPopularMovie.items.clear()
            adapterPopularMovie.items.addAll(items)
            adapterPopularMovie.notifyDataSetChanged()
            return
        }

        decoration.realItemCount = items.size
        adapterPopularMovie.items.clear()
        adapterPopularMovie.items.addAll(items)
        adapterPopularMovie.notifyDataSetChanged()

        items.size.takeIf { size -> size > 1 }?.apply {
            binding.recyclerViewCollectionPopularMovie.addOnScrollListener(HorizontalScrollListener(this){scrollState ->
                if (scrollState == RecyclerView.SCROLL_STATE_IDLE) resetAutoScroll()
                isScrolling = scrollState != RecyclerView.SCROLL_STATE_IDLE
            })
        }

        binding.popularMovieShimmer.visibility                = View.INVISIBLE
        binding.recyclerViewCollectionPopularMovie.visibility = View.VISIBLE
        binding.recyclerViewCollectionPopularMovie.scrollToPosition(2)
        runAutoScroll(items.size)
        binding.executePendingBindings()
    }

    private val scroller = Runnable {
        if (!isScrolling){
            binding.recyclerViewCollectionPopularMovie.let {
                it.smoothScrollToPosition(((it.layoutManager as? LinearLayoutManager)?.findFirstCompletelyVisibleItemPosition() ?: 0) + 1)
            }
        }
    }

    private fun runAutoScroll(size: Int){
        if (size < 2) return
        handler?.postDelayed(scroller, AUTO_SCROLL_PERIOD)
    }

    private fun resetAutoScroll(){
        handler?.removeCallbacks(scroller)
        handler?.postDelayed(scroller, AUTO_SCROLL_PERIOD)
    }

    class HorizontalScrollListener (private val itemCount: Int, private val stateChange: (Int) -> Unit) : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (dx == 0 && dy == 0) return
            when ((recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()) {
                (itemCount - 2) -> recyclerView.scrollToPosition(2)
                1 -> recyclerView.scrollToPosition(itemCount - 3)
                (itemCount - 1) -> recyclerView.scrollToPosition(3)
                0 -> recyclerView.scrollToPosition(itemCount - 4)
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            stateChange(newState)
        }
    }

    companion object{
        private const val AUTO_SCROLL_PERIOD = 3000L
    }
}