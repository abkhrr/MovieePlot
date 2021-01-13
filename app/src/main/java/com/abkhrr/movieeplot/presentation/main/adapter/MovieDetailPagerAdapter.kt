package com.abkhrr.movieeplot.presentation.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abkhrr.movieeplot.presentation.main.fragment.information.FragmentInformation
import com.abkhrr.movieeplot.presentation.main.fragment.review.FragmentReview

class MovieDetailPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0    -> FragmentInformation()
            1    -> FragmentReview()
            else -> FragmentInformation()
        }
    }

}