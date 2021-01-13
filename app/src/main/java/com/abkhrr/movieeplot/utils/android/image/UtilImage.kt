package com.abkhrr.movieeplot.utils.android.image

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DimenRes
import com.abkhrr.movieeplot.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.*
import com.bumptech.glide.request.RequestOptions

object UtilImage {

    fun loadImageWithoutPlaceholder(view: ImageView?, imageUrl: String?, context: Context, @DimenRes radius: Int? = null) {
        val multiTransformation =  MultiTransformation(CenterCrop(), RoundedCorners(context.resources.getDimensionPixelSize(radius ?: R.dimen.SIZE_CORNER_NULL)))
        view?.let {
            Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions.bitmapTransform(multiTransformation))
                .into(view)
        }
    }

    fun loadImagePoster(view: ImageView?, imageUrl: String?, context: Context) {
        view?.let {
            Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions().override(1080,1920)) // Normal 500,750
                .into(view)
        }
    }

    fun loadImageWithPlaceholder(view: ImageView?, imageUrl: String?, context: Context) {
        val multiTransformation = MultiTransformation(CenterCrop())
        val safeImagePath = if (!imageUrl.isNullOrBlank()) imageUrl else ""
        view?.let {
            Glide.with(context)
                .load(safeImagePath)
                .apply(RequestOptions.bitmapTransform(multiTransformation))
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(view)
        }
    }
}
