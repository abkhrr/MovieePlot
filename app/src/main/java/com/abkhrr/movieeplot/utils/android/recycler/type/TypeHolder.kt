package com.abkhrr.movieeplot.utils.android.recycler.type

object TypeHolder {

    const val ITEM_TOOLBAR    = -1
    const val ITEM_POPULAR    = -2
    const val ITEM_UPCOMING   = -3
    private const val OTHER   = -4

    //HOLDER ITEM NAME
    const val HOLDER_NAME_TOOLBAR  = "TOOLBAR"
    const val HOLDER_NAME_POPULAR  = "POPULAR"
    const val HOLDER_NAME_UPCOMING = "UPCOMING"
    const val HOLDER_NAME_OTHER    = "OTHER"


    fun getDashboardHolder(holderName: Any) = when (holderName) {
        HOLDER_NAME_TOOLBAR  -> ITEM_TOOLBAR
        HOLDER_NAME_POPULAR  -> ITEM_POPULAR
        HOLDER_NAME_UPCOMING -> ITEM_UPCOMING
        else                 -> OTHER
    }

}