package com.abkhrr.movieeplot.presentation.main.fragment.review

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abkhrr.movieeplot.BR
import com.abkhrr.movieeplot.R
import com.abkhrr.movieeplot.databinding.FragmentReviewBinding
import com.abkhrr.movieeplot.factory.ViewModelProviderFactory
import com.abkhrr.movieeplot.presentation.base.BaseFragment
import com.abkhrr.movieeplot.presentation.main.adapter.ReviewAdapter
import com.abkhrr.movieeplot.presentation.main.viewmodel.SharedViewModel
import javax.inject.Inject

class FragmentReview : BaseFragment<FragmentReviewBinding, SharedViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_review

    override val viewModel: SharedViewModel
        get() = ViewModelProvider(this, factory).get(SharedViewModel::class.java)

    private var movieId: Int? = null
    private var reviewAdapter = ReviewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (parentFragment?.arguments != null){
            movieId = parentFragment?.arguments?.getInt("movieId")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModelData()
        setupRecyclerView()
    }

    private fun initViewModelData(){
        movieId?.let { viewModel.fetchMovieReview(it) }
        viewModel.movieReviewData.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()){
                reviewAdapter.items.clear()
                reviewAdapter.items.addAll(it)
                reviewAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun setupRecyclerView(){
        val layoutManager                     = LinearLayoutManager(activity)
        layoutManager.orientation             = RecyclerView.VERTICAL

        getViewDataBinding().viewReviewCollectionRecyclerview.adapter        = reviewAdapter
        getViewDataBinding().viewReviewCollectionRecyclerview.layoutManager  = layoutManager
        getViewDataBinding().viewReviewCollectionRecyclerview.itemAnimator   = null
        getViewDataBinding().lifecycleOwner                                  = this

        getViewDataBinding().viewReviewSwipeRefreshLayout.setOnRefreshListener {
            getViewDataBinding().viewReviewSwipeRefreshLayout.isRefreshing = false
            initViewModelData()
        }
    }

}