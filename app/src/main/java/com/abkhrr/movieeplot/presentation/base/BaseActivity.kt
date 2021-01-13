package com.abkhrr.movieeplot.presentation.base

import com.abkhrr.movieeplot.R
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.abkhrr.movieeplot.service.NetworkMonitor
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


abstract class BaseActivity<T: ViewDataBinding, V: ViewModel> : AppCompatActivity(),
    HasAndroidInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    abstract val bindingVariable: Int

    @get:LayoutRes
    abstract val layoutId: Int

    abstract val viewModel: V

    abstract val isNeedOnline: Boolean

    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var alertDialog: AlertDialog
    private var isAlreadyVisible: Boolean = false

    // For Alert Dialog //
    private val cancelable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        initDialog()
        performDataBinding()
        checkConnection()
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    private fun performDataBinding() {
        val viewDataBinding = DataBindingUtil.setContentView<T>(this, layoutId)
        viewDataBinding.setVariable(bindingVariable, viewModel)
        viewDataBinding.executePendingBindings()
    }

    private fun initDialog(){
        dialogBuilder = AlertDialog.Builder(ContextThemeWrapper(this, android.R.style.ThemeOverlay_Material_Dialog))
    }

    private fun checkConnection(){
        if (isNeedOnline){
            val connectionLiveData = NetworkMonitor(this)
            connectionLiveData.observe(this) { isConnected ->
                if (isConnected) {
                    hideDisplayInternetNotAvailable()
                    isAlreadyVisible = false
                } else {
                    if (!isAlreadyVisible) {
                        displayInternetNotAvailable()
                        isAlreadyVisible = true
                    }
                }
            }
        }
    }

    private fun displayInternetNotAvailable(){
        val layoutInflater   = layoutInflater
        val layoutView: View = layoutInflater.inflate(R.layout.dialog_no_internet, null)

        dialogBuilder.setCancelable(false)
        dialogBuilder.setView(layoutView)

        alertDialog   = dialogBuilder.create()
        alertDialog.window?.attributes?.windowAnimations = R.style.SlideInOutAnimation
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
    }

    private fun hideDisplayInternetNotAvailable(){
        if(isAlreadyVisible)
        {
            alertDialog.dismiss()
        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentDispatchingAndroidInjector
    }

}