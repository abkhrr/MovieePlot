package com.abkhrr.movieeplot.di.provider

import com.abkhrr.movieeplot.presentation.main.fragment.detail.FragmentMovieDetail
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MovieDetailFragmentProvider {
    @ContributesAndroidInjector
    abstract fun provideFragmentMovieDetailFactory(): FragmentMovieDetail
}