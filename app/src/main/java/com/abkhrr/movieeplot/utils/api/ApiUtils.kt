package com.abkhrr.movieeplot.utils.api

class ApiUtils {

    fun getErrorMessage(codes: Int?, type: TypeError, fourHundredError: String): String {
        when(type) {
            TypeError.ERROR_SOCKET -> {
                return "No Internet. Please double check your connection..."
            }
            TypeError.ERROR_HTTP -> {
                return when(codes){
                    400  -> { fourHundredError }
                    500  -> { "Something went wrong.. Please try again later.." }
                    502  -> { "Something went wrong.. Please try again later.." }
                    503  -> { "Something went wrong.. Please try again later.." }
                    else -> { "Server Error." }
                }
            }
            TypeError.NETWORK_ERROR -> {
                return "Terjadi Kesalahan"
            }
        }
    }

}