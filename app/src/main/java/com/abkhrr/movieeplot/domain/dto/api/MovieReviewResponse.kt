package com.abkhrr.movieeplot.domain.dto.api
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieReviewResponse(

    @SerializedName("id")
    var id: Int,

    @SerializedName("page")
    var page: Int,

    @SerializedName("results")
    var results: List<MovieReviewResult>,

    @SerializedName("total_pages")
    var totalPages: Int,

    @SerializedName("total_results")
    var totalResults: Int

): Parcelable

@Parcelize
data class MovieReviewResult(
    @SerializedName("author")
    var author: String,
    @SerializedName("author_details")
    var authorDetails: AuthorDetails,
    @SerializedName("content")
    var content: String,
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("id")
    var id: String,
    @SerializedName("updated_at")
    var updatedAt: String,
    @SerializedName("url")
    var url: String
): Parcelable

@Parcelize
data class AuthorDetails(
    @SerializedName("avatar_path")
    var avatarPath: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("rating")
    var rating: Double,
    @SerializedName("username")
    var username: String
): Parcelable