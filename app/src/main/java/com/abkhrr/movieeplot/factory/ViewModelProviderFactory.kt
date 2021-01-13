package com.abkhrr.movieeplot.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abkhrr.movieeplot.data.source.AppDataSource
import com.abkhrr.movieeplot.presentation.main.viewmodel.SharedViewModel
import javax.inject.Inject

class ViewModelProviderFactory @Inject constructor(
        private val application: Application,
        private val appDataSource: AppDataSource
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SharedViewModel::class.java) -> {
                SharedViewModel(application, appDataSource) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}