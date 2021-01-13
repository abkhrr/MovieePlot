package com.abkhrr.movieeplot.di.provider

import com.abkhrr.movieeplot.presentation.main.fragment.information.FragmentInformation
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InformationFragmentProvider {
    @ContributesAndroidInjector
    abstract fun provideFragmentInformationFactory(): FragmentInformation
}