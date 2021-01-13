package com.abkhrr.movieeplot.di.provider

import com.abkhrr.movieeplot.presentation.main.fragment.detail.master.FragmentMasterHost
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MasterNavHostProvider {
    @ContributesAndroidInjector
    abstract fun provideFragmentMasterHostFactory(): FragmentMasterHost
}