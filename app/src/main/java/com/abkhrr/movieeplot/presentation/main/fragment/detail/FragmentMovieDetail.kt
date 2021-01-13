package com.abkhrr.movieeplot.presentation.main.fragment.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.abkhrr.movieeplot.BR
import com.abkhrr.movieeplot.R
import com.abkhrr.movieeplot.databinding.FragmentMovieDetailBinding
import com.abkhrr.movieeplot.factory.ViewModelProviderFactory
import com.abkhrr.movieeplot.presentation.base.BaseFragment
import com.abkhrr.movieeplot.presentation.main.activity.MainActivity
import com.abkhrr.movieeplot.presentation.main.adapter.MovieDetailPagerAdapter
import com.abkhrr.movieeplot.presentation.main.viewmodel.SharedViewModel
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class FragmentMovieDetail : BaseFragment<FragmentMovieDetailBinding, SharedViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_movie_detail

    override val viewModel: SharedViewModel
        get() = ViewModelProvider(this, factory).get(SharedViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        setThemes()
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabs()
    }

    private fun setThemes(){
        val mainActivity = activity as MainActivity
        mainActivity.isNeedFullScreen(false)
    }

    private fun setupTabs(){
        val tabLayout  = getViewDataBinding().tabLayoutMovieDetail
        val viewPager  = getViewDataBinding().movieDetailViewPager

        viewPager.adapter            = MovieDetailPagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)
        viewPager.isUserInputEnabled = false

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Information"
                1 -> tab.text = "Review"
            }
        }.attach()
    }

    override fun onDestroyView() {
        val viewPager2 = getViewDataBinding().movieDetailViewPager
        viewPager2.adapter = null
        super.onDestroyView()
    }

}