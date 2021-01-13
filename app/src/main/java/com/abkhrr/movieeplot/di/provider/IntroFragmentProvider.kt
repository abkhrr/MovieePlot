package com.abkhrr.movieeplot.di.provider

import com.abkhrr.movieeplot.presentation.main.fragment.intro.FragmentIntro
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class IntroFragmentProvider {
    @ContributesAndroidInjector
    abstract fun provideFragmentProviderFactory(): FragmentIntro
}