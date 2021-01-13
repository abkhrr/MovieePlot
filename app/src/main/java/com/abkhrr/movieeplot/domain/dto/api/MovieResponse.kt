package com.abkhrr.movieeplot.domain.dto.api
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    @SerializedName("adult")
    var adult: Boolean,

    @SerializedName("backdrop_path")
    var backdropPath: String,

    @SerializedName("belongs_to_collection")
    var belongsToCollection: BelongsToCollection,

    @SerializedName("budget")
    var budget: Int,

    @SerializedName("genres")
    var genres: List<Genre>,

    @SerializedName("homepage")
    var homepage: String,

    @SerializedName("id")
    var id: Int,

    @SerializedName("imdb_id")
    var imdbId: String,

    @SerializedName("original_language")
    var originalLanguage: String,

    @SerializedName("original_title")
    var originalTitle: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("poster_path")
    var posterPath: String,

    @SerializedName("production_companies")
    var productionCompanies: List<ProductionCompany>,

    @SerializedName("production_countries")
    var productionCountries: List<ProductionCountry>,

    @SerializedName("release_date")
    var releaseDate: String,

    @SerializedName("revenue")
    var revenue: Int,

    @SerializedName("runtime")
    var runtime: Int,

    @SerializedName("spoken_languages")
    var spokenLanguages: List<SpokenLanguage>,

    @SerializedName("status")
    var status: String,

    @SerializedName("tagline")
    var tagline: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("video")
    var video: Boolean,

    @SerializedName("vote_average")
    var voteAverage: Double,

    @SerializedName("vote_count")
    var voteCount: Int
): Parcelable

@Parcelize
data class BelongsToCollection(
    @SerializedName("backdrop_path")
    var backdropPath: String,

    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("poster_path")
    var posterPath: String
): Parcelable

@Parcelize
data class Genre(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String
): Parcelable

@Parcelize
data class ProductionCompany(
    @SerializedName("id")
    var id: Int,

    @SerializedName("logo_path")
    var logoPath: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("origin_country")
    var originCountry: String
): Parcelable

@Parcelize
data class ProductionCountry(
    @SerializedName("iso_3166_1")
    var iso31661: String,

    @SerializedName("name")
    var name: String
): Parcelable

@Parcelize
data class SpokenLanguage(
    @SerializedName("english_name")
    var englishName: String,

    @SerializedName("iso_639_1")
    var iso6391: String,

    @SerializedName("name")
    var name: String
): Parcelable