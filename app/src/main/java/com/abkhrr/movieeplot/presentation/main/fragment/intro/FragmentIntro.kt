package com.abkhrr.movieeplot.presentation.main.fragment.intro

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.abkhrr.movieeplot.BR
import com.abkhrr.movieeplot.R
import com.abkhrr.movieeplot.databinding.FragmentIntroBinding
import com.abkhrr.movieeplot.factory.ViewModelProviderFactory
import com.abkhrr.movieeplot.presentation.base.BaseFragment
import com.abkhrr.movieeplot.presentation.base.navigation.NavigationCommand
import com.abkhrr.movieeplot.presentation.main.activity.MainActivity
import com.abkhrr.movieeplot.presentation.main.viewmodel.SharedViewModel
import com.abkhrr.movieeplot.utils.extension.afterTextChanged
import java.util.*
import javax.inject.Inject

class FragmentIntro : BaseFragment<FragmentIntroBinding, SharedViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_intro

    override val viewModel: SharedViewModel
        get() = ViewModelProvider(this, factory).get(SharedViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        setThemes()
        super.onCreate(savedInstanceState)
    }

    private fun setThemes(){
        val mainActivity = activity as MainActivity
        mainActivity.isNeedFullScreen(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        checkTextUsername()
        buttonOnClick()
    }

    private fun checkTextUsername(){
        val editText = getViewDataBinding().edtTextUsername
        editText.afterTextChanged { username ->
            if(hasLength(username) && hasUpperCase(username)) {
                val text = resources.getString(R.string.content_second_intro)
                getViewDataBinding().errorIntroOutput.visibility            = View.INVISIBLE
                getViewDataBinding().iconChecked.visibility                 = View.VISIBLE
                getViewDataBinding().secondLayoutIntro.root.visibility      = View.VISIBLE
                getViewDataBinding().secondLayoutIntro.secondIntroText.text = String.format(text, username)
                viewModel.setSession(username, true, requireContext())
            } else {
                getViewDataBinding().errorIntroOutput.visibility            = View.VISIBLE
                getViewDataBinding().iconChecked.visibility                 = View.INVISIBLE
                getViewDataBinding().secondLayoutIntro.root.visibility      = View.GONE
            }
        }
    }

    private fun buttonOnClick(){
        getViewDataBinding().secondLayoutIntro.buttonContinue.setOnClickListener {
            navigate(NavigationCommand.To(FragmentIntroDirections.toFragmentDashboard()))
        }
    }

    private fun hasLength(data: CharSequence): Boolean {
        return data.toString().length >= 6
    }

    private fun hasUpperCase(data: CharSequence): Boolean {
        val password = data.toString()
        return password != password.toLowerCase(Locale.ROOT)
    }

}