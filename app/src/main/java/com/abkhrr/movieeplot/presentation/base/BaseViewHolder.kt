package com.abkhrr.movieeplot.presentation.base

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    abstract fun onLifeCycleStart()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    abstract fun onLifeCycleStop()
}