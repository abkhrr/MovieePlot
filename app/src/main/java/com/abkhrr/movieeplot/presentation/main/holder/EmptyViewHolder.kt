package com.abkhrr.movieeplot.presentation.main.holder

import androidx.recyclerview.widget.RecyclerView
import com.abkhrr.movieeplot.databinding.ViewHolderEmptyViewBinding

class EmptyViewHolder(private val binding: ViewHolderEmptyViewBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(){
        binding.executePendingBindings()
    }
}