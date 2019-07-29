package com.example.itunesalbumssearch.api

import com.example.itunesalbumssearch.AlbumModel
import com.example.itunesalbumssearch.TrackModel

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface APIService {
    @GET("search")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun getAlbums(@Query("term") term: String, @Query ("entity") entity: String): Call<AlbumModel>

    @GET("lookup")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun getAlbumTracks(@Query ("id") id: String, @Query("entity") entity: String): Call<TrackModel>
}