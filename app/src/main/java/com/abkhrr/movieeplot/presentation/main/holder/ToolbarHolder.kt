package com.abkhrr.movieeplot.presentation.main.holder

import androidx.recyclerview.widget.RecyclerView
import com.abkhrr.movieeplot.R
import com.abkhrr.movieeplot.databinding.ToolbarHolderBinding
import com.abkhrr.movieeplot.utils.helper.SessionHelper
import java.util.*

class ToolbarHolder(private val binding: ToolbarHolderBinding): RecyclerView.ViewHolder(binding.root) {

    private val sessionHelper = SessionHelper(binding.root.context)

    fun bind(){
        binding.greetingUname.text = appUsername()
        binding.signOutButton.setOnClickListener { sessionHelper.endSession() }
        binding.executePendingBindings()
    }

    private fun appUsername(): String {
        val username = sessionHelper.getUsername()
        return String.format(binding.root.context.getString(R.string.toolbar_greetings), getTime(), username)
    }

    private fun getTime(): String {
        val c: Calendar = Calendar.getInstance()
        return when (c.get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> {
                "Good Morning"
            }
            in 12..15 -> {
                "Good Afternoon"
            }
            else -> {
                "Good Evening"
            }
        }
    }

}