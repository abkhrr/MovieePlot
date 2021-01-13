package com.abkhrr.movieeplot.di.builder

import com.abkhrr.movieeplot.di.provider.*
import com.abkhrr.movieeplot.presentation.main.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    // First Form Activity
    @ContributesAndroidInjector(modules = [
        SplashScreenFragmentProvider::class,
        IntroFragmentProvider::class,
        DashboardFragmentProvider::class,
        MovieDetailFragmentProvider::class,
        InformationFragmentProvider::class,
        ReviewFragmentProvider::class,
        MasterNavHostProvider::class
    ])
    abstract fun bindMainActivity(): MainActivity

}