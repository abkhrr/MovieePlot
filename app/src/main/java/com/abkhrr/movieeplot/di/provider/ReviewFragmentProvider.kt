package com.abkhrr.movieeplot.di.provider

import com.abkhrr.movieeplot.presentation.main.fragment.review.FragmentReview
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ReviewFragmentProvider {
    @ContributesAndroidInjector
    abstract fun provideFragmentReviewFactory(): FragmentReview
}