package com.abkhrr.movieeplot.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abkhrr.movieeplot.BuildConfig
import com.abkhrr.movieeplot.databinding.ViewItemReviewBinding
import com.abkhrr.movieeplot.domain.dto.api.MovieReviewResult
import com.abkhrr.movieeplot.utils.android.image.UtilImage
import com.abkhrr.movieeplot.utils.constant.AppConstant
import com.google.android.material.snackbar.Snackbar

class ReviewAdapter: RecyclerView.Adapter<ReviewAdapter.ReviewHolder>() {

    val items: MutableList<MovieReviewResult> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ReviewHolder(ViewItemReviewBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ReviewHolder(private val binding: ViewItemReviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            val context    = binding.root.context
            val review     = items[position]
            binding.review = review
            checkIsNotNull(review)
            val imageUrl   = checkImageUrl(review)
            UtilImage.loadImageWithPlaceholder(binding.authorImage, imageUrl, context)
            binding.executePendingBindings()
        }

        private fun checkImageUrl(review: MovieReviewResult): String {
            return if(review.authorDetails.avatarPath.contains("/https://")){
                review.authorDetails.avatarPath.substring(0)
            } else {
                BuildConfig.IMAGE_BASE_URL + review.authorDetails.avatarPath
            }
        }

        private fun checkIsNotNull(review: MovieReviewResult){
            if (isNullOrEmpty(review.authorDetails.avatarPath)){
                review.authorDetails.avatarPath = "/klZ9hebmc8biG1RC4WmzNFnciJN.jpg"
            }
        }

        private fun isNullOrEmpty(str: String?): Boolean {
            if (str != null && str.trim().isNotEmpty())
                return false
            return true
        }
    }
}