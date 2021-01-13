package com.abkhrr.movieeplot.di.provider

import com.abkhrr.movieeplot.presentation.main.fragment.dashboard.FragmentDashboard
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DashboardFragmentProvider {
    @ContributesAndroidInjector
    abstract fun provideFragmentDashboardFactory(): FragmentDashboard
}