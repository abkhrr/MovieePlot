package com.abkhrr.movieeplot.presentation.main.fragment.dashboard

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abkhrr.movieeplot.BR
import com.abkhrr.movieeplot.R
import com.abkhrr.movieeplot.databinding.FragmentDashboardBinding
import com.abkhrr.movieeplot.domain.dto.api.PopularMovieResult
import com.abkhrr.movieeplot.domain.dto.api.UpcomingMovieResult
import com.abkhrr.movieeplot.factory.ViewModelProviderFactory
import com.abkhrr.movieeplot.presentation.base.BaseFragment
import com.abkhrr.movieeplot.presentation.base.navigation.NavigationCommand
import com.abkhrr.movieeplot.presentation.main.activity.MainActivity
import com.abkhrr.movieeplot.presentation.main.adapter.DashboardAdapter
import com.abkhrr.movieeplot.presentation.main.listener.DashboardListener
import com.abkhrr.movieeplot.presentation.main.viewmodel.SharedViewModel
import com.abkhrr.movieeplot.utils.android.recycler.ParallaxScroll
import javax.inject.Inject

class FragmentDashboard : BaseFragment<FragmentDashboardBinding, SharedViewModel>(), DashboardListener {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var dashboardAdapter: DashboardAdapter

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_dashboard

    override val viewModel: SharedViewModel
        get() = ViewModelProvider(this, factory).get(SharedViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        setThemes()
        super.onCreate(savedInstanceState)
        dashboardAdapter = DashboardAdapter(this.lifecycle, this)
    }

    private fun setThemes(){
        val mainActivity = activity as MainActivity
        mainActivity.isNeedFullScreen(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModelData()
        initView()
        setupRecyclerView()
    }

    private fun initViewModelData(){
        viewModel.popularMovieData.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()){
                dashboardAdapter.popularMovie.clear()
                dashboardAdapter.popularMovie.addAll(it)
                dashboardAdapter.notifyDataSetChanged()
            }
        })

        viewModel.upcomingMovieData.observe(viewLifecycleOwner, {
            dashboardAdapter.upcomingMovie.clear()
            dashboardAdapter.upcomingMovie.addAll(it)
            dashboardAdapter.notifyDataSetChanged()
        })
    }

    private fun initView(){
        viewModel.fetchUpcomingMovie()
        viewModel.fetchUpcomingMovie()
    }

    private fun setupRecyclerView(){
        val layoutManager                     = LinearLayoutManager(activity)
        layoutManager.orientation             = RecyclerView.VERTICAL
        dashboardAdapter.realMenuPositionList = viewModel.getRecyclerPositionList()

        getViewDataBinding().viewDashboardCollectionRecyclerview.adapter        = dashboardAdapter
        getViewDataBinding().viewDashboardCollectionRecyclerview.layoutManager  = layoutManager
        getViewDataBinding().viewDashboardCollectionRecyclerview.itemAnimator   = null
        getViewDataBinding().lifecycleOwner                                     = this
        getViewDataBinding().viewDashboardCollectionRecyclerview.addOnScrollListener(ParallaxScroll())

        getViewDataBinding().viewDashboardSwipeRefreshLayout.setOnRefreshListener {
            getViewDataBinding().viewDashboardSwipeRefreshLayout.isRefreshing = false
            viewModel.fetchPopularMovie()
            viewModel.fetchUpcomingMovie()
        }
    }

    override fun onPopularMovieClick(movie: PopularMovieResult) {
        navigate(NavigationCommand.To(FragmentDashboardDirections.toFragmentDetails(movie.id)))
    }

    override fun onUpcomingMovieClick(movie: UpcomingMovieResult) {
        navigate(NavigationCommand.To(FragmentDashboardDirections.toFragmentDetails(movie.id)))
    }
}