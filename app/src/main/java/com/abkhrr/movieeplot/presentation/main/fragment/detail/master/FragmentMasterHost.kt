package com.abkhrr.movieeplot.presentation.main.fragment.detail.master

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.abkhrr.movieeplot.BR
import com.abkhrr.movieeplot.R
import com.abkhrr.movieeplot.databinding.FragmentMasterHostBinding
import com.abkhrr.movieeplot.factory.ViewModelProviderFactory
import com.abkhrr.movieeplot.presentation.base.BaseFragment
import com.abkhrr.movieeplot.presentation.main.viewmodel.SharedViewModel
import javax.inject.Inject

class FragmentMasterHost : BaseFragment<FragmentMasterHostBinding, SharedViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_master_host

    override val viewModel: SharedViewModel
        get() = ViewModelProvider(this, factory).get(SharedViewModel::class.java)

    private val nestedNavHostFragmentId = R.id.nested_movie_detail_fragment
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nestedNavHostFragment = childFragmentManager.findFragmentById(nestedNavHostFragmentId) as? NavHostFragment
        nestedNavHostFragment?.let {
            navController = it.navController
        }
        listenOnBackPressed()
    }

    private fun listenOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onResume() {
        super.onResume()
        callback.isEnabled = true
    }

    override fun onPause() {
        super.onPause()
        callback.isEnabled = false
    }

    val callback = object : OnBackPressedCallback(false) {

        override fun handleOnBackPressed() {
            if (navController.currentDestination?.id == navController.graph.startDestination) {
                isEnabled = false
                requireActivity().onBackPressed()
                isEnabled = true
            } else {
                navController.navigateUp()
            }
        }
    }

}