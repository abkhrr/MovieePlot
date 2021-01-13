package com.abkhrr.movieeplot.domain.dto.api.result

import com.abkhrr.movieeplot.utils.api.TypeError

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val message: String?, val statusCode: Int? = null, val typeError: TypeError) : Result<Nothing>()
}