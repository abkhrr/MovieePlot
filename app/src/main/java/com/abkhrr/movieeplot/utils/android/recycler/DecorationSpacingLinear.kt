package com.abkhrr.movieeplot.utils.android.recycler

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import com.abkhrr.movieeplot.R

class DecorationSpacingLinear(context: Context?, @DimenRes dividerResource : Int) : RecyclerView.ItemDecoration() {

    private var isCircular = false
    constructor(context: Context?, @DimenRes dividerResource : Int, isCircular: Boolean) : this(context, dividerResource){
        this.isCircular = isCircular
    }
    private val dividerSize: Int = context?.resources?.getDimensionPixelSize(dividerResource) ?: 0
    private val dividerVertical: Int = context?.resources?.getDimensionPixelSize(R.dimen.PADDING_DEXTAR) ?: 0
    private val dividerVerticalCircular: Int = context?.resources?.getDimensionPixelSize(R.dimen.PADDING_QUARTER) ?: 0

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val posIndex    = parent.getChildAdapterPosition(view)
        outRect.top     = dividerVertical
        outRect.bottom  = if (isCircular) dividerVerticalCircular else dividerVertical
        outRect.right   = dividerSize
        if (isCircular) {outRect.left = dividerSize; return}
        if (posIndex == 0) { outRect.left = dividerVertical }
    }
}