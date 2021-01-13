package com.abkhrr.movieeplot.di.provider

import com.abkhrr.movieeplot.presentation.main.fragment.splash.FragmentSplashScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SplashScreenFragmentProvider {
    @ContributesAndroidInjector
    abstract fun provideFragmentSplashFactory(): FragmentSplashScreen
}