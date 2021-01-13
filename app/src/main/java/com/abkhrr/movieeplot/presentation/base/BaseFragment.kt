package com.abkhrr.movieeplot.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abkhrr.movieeplot.presentation.base.navigation.NavigationCommand
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {

    private lateinit var viewDataBinding: T
    abstract val bindingVariable: Int

    @get:LayoutRes
    abstract val layoutId: Int

    abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onStart() {
        super.onStart()
        viewModel.showToast.observe(this, { Toast.makeText(activity, it, Toast.LENGTH_LONG).show() })
        viewModel.showSnack.observe(this, { Snackbar.make(this.requireView(), it, Snackbar.LENGTH_LONG).show() })
    }

    fun getViewDataBinding(): T {
        return viewDataBinding
    }

    fun navigate(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.To     -> findNavController().navigate(command.directions)
            is NavigationCommand.Back   -> findNavController().popBackStack()
            is NavigationCommand.BackTo -> findNavController().popBackStack(command.destinationId, false)
        }
    }

    private var onBackPressedCallback: OnBackPressedCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        getViewDataBinding().setVariable(bindingVariable, viewModel)
        getViewDataBinding().lifecycleOwner = this
        getViewDataBinding().executePendingBindings()
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }
}