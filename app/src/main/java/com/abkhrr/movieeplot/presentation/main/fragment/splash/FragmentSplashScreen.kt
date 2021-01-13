package com.abkhrr.movieeplot.presentation.main.fragment.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.abkhrr.movieeplot.BR
import com.abkhrr.movieeplot.R
import com.abkhrr.movieeplot.databinding.FragmentSplashScreenBinding
import com.abkhrr.movieeplot.factory.ViewModelProviderFactory
import com.abkhrr.movieeplot.presentation.base.BaseFragment
import com.abkhrr.movieeplot.presentation.base.navigation.NavigationCommand
import com.abkhrr.movieeplot.presentation.main.activity.MainActivity
import com.abkhrr.movieeplot.presentation.main.viewmodel.SharedViewModel
import com.abkhrr.movieeplot.utils.helper.SessionHelper
import javax.inject.Inject

class FragmentSplashScreen : BaseFragment<FragmentSplashScreenBinding, SharedViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_splash_screen

    override val viewModel: SharedViewModel
        get() = ViewModelProvider(this, factory).get(SharedViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        setThemes()
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scheduleSplashScreen()
    }

    private fun setThemes(){
        val mainActivity = activity as MainActivity
        mainActivity.isNeedFullScreen(true)
    }

    private fun scheduleSplashScreen(){
        Handler(Looper.getMainLooper()).postDelayed({
            if (!hasSession()) {
                navigate(NavigationCommand.To(FragmentSplashScreenDirections.toFragmentIntro()))
            } else {
                navigate(NavigationCommand.To(FragmentSplashScreenDirections.toFragmentDashboard()))
            }
        }, 2000)
    }

    private fun hasSession(): Boolean {
        val sessionHelper = SessionHelper(requireContext())
        return sessionHelper.hasSession()
    }
}