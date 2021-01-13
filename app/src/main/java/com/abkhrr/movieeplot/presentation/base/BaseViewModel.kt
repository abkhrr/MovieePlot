package com.abkhrr.movieeplot.presentation.base

import androidx.lifecycle.ViewModel
import com.idcoding.laporan.konsumen.utils.event.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {
    val isLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showToast: SingleLiveEvent<String>  = SingleLiveEvent()
    val showSnack: SingleLiveEvent<String>  = SingleLiveEvent()
}